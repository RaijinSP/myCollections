package com.raijin.collections;

import java.util.*;


/*Almost my own linked list implementation, was done as part of studying collections framework*/
public class LinkedList<T> implements List<T> {

    private Item<T> first = null;

    private Item<T> last = null;

    private int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (Item<T> item = first; item != null; item = item.getNextItem()) {
            if (Objects.equals(o, item.element)) return true;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator(0);
    }

    @Override
    public Object[] toArray() {
        T[] items = (T[]) new Object[this.size()];
        int index = 0;
        for (Item<T> item = first; item != null; item = item.getNextItem()) {
            items[index++] = item.element;
        }
        return items;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) throw new NullPointerException();
        if (a.length < this.size()) a = Arrays.copyOf(a, this.size());
        if (a.length > this.size()) a[this.size()] = null;
        System.arraycopy((T1[])this.toArray(), 0, a,0, this.size());
        return a;
    }

    @Override
    public boolean add(final T newElement) {
        Item<T> item = new Item<T>(newElement, last, null);
        if (this.size > 0) {
            last.setNextItem(item);
            last = item;
        }
        if (this.size == 0) first = last = item;
        this.size++;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        if (this.size == 0) return false;
        for (Item<T> item = first; item != null; item = item.getNextItem()) {
            if (o.equals(item.element)) {
                unlink(item);
                return true;
            }
        }
        return false;
    }

    private void unlink(Item<T> item) {
        size--;
        if (item == first && item == last) {
            first = last = null;
            return;
        }
        if (item == first) first = item.getNextItem();
        else if (item == last) last = item.getPrevItem();
        else {
            item.getNextItem().setPrevItem(item.getPrevItem());
            item.getPrevItem().setPrevItem(item.getNextItem());
        }
        item.setNextItem(null);
        item.setPrevItem(null);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final T item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        for (Item<T> item = first; item != null; item = item.getNextItem()) {
            item.setNextItem(null);
            item.setPrevItem(null);
        }
        first = null;
        last = null;
        this.size = 0;
    }

    @Override
    public T remove(final int index) throws IndexOutOfBoundsException{
        T tmp = this.get(index);
        this.remove(tmp);
        return tmp;
    }

    private void remove(final Item<T> current) {
        for (Item<T> item = first; item != null; item = item.getNextItem()) {
            if (item.equals(current)) {
                this.remove(item.element);
            }
        }
    }

    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {

        for (Item<T> item = last; item != null; item = item.getPrevItem()) if (item.equals(target)) return indexOf(item);
        return -1;

    }

    @Override
    public int indexOf(final Object o) {
        int index = 0;
        for (Item<T> item = first; item != null; item = item.getNextItem()) {
            if (Objects.equals(o, item.element)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public void add(final int index, final T element) {
        if (index >= size() || index < 0) throw new IndexOutOfBoundsException();
        if (element == null) throw new IllegalStateException();
        Item<T> old = getItemByIndex(index);
        Item<T> newItem = new Item<>(element, old.getPrevItem(), old);
        old.getPrevItem().setNextItem(newItem);
        old.setPrevItem(newItem);
        size++;
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        if (index >= size() || index < 0) throw new IndexOutOfBoundsException();
        if (elements == null) throw new IllegalStateException();
        int iter = index;
        for (Object el: elements) {
            add(iter++, (T)el);
        }
        return true;
    }

    @Override
    public T set(final int index, final T element) {
        T el = this.getItemByIndex(index).element;
        this.getItemByIndex(index).element = element;
        return el;
    }

    @Override
    public T get(final int index) {
        return this.getItemByIndex(index).element;
    }

    private Item<T> setFirst (int index) {
        return this.getItemByIndex(index);
    }

    private Item<T> getItemByIndex(final int index) {
        int cursor = 0;
        for (Item<T> item = first; item != null; item = item.getNextItem()) {
            if (index == cursor) return item;
            cursor++;
        }

        throw new IndexOutOfBoundsException();
    }

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> curr;

        private Item<T> last;

        private int index;

        private int cursor = -1;

        ElementsIterator(final int index) {
            this.index = index;
            curr = (index == size) ? null : setFirst(index);
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            last = curr;
            curr = last.getNextItem();
            cursor = index;
            index++;
            return last.element;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            curr = (curr == null) ? last : curr.getPrevItem();
            last = curr;
            cursor = index;
            index--;
            return last.element;
        }

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
            if (cursor < 0) {
                throw new IllegalStateException();
            }
            last.element = element;
            cursor = -1;
        }

        @Override
        public int previousIndex(){
            return index - 1;
        }
        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public void remove() {
            if (cursor < 0)  throw new IllegalStateException();
            LinkedList.this.remove(last.element);
            cursor = -1;
        }
    }

    private static class Item<T> {

        private T element;

        private Item<T> next;

        private Item<T> prev;

        Item(final T element, final Item<T> prev, final Item<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public Item<T> getNextItem() {
            return next;
        }

        public Item<T> getPrevItem() {
            return prev;
        }

        public void setNextItem(Item<T> item) {
            this.next = item;
        }

        public void setPrevItem(Item<T> item) {
            this.prev = item;
        }
    }
}
