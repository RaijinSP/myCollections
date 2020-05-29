package com.raijin.collections;

import java.util.*;

/*HashSet*/
public class MyHashSet<T> implements Set<T> {

    private static final Boolean EXIST = true;

    private final Map<T, Boolean> elements = new HashMap<>();

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return elements.containsKey(o);
    }

    @Override
    public Iterator<T> iterator() {
        return elements.keySet().iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.keySet().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return elements.keySet().toArray(a);
    }

    @Override
    public boolean add(T t) {
        return elements.put(t, EXIST) == null;
    }

    @Override
    public boolean remove(Object o) {
        return elements.remove(o) == EXIST;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elements.keySet().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T item: c) {
            this.add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;
        for (Object item: c) {
            if (this.remove(item)) removed = true;
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return elements.keySet().retainAll(c);
    }

    @Override
    public void clear() {
        elements.clear();
    }
}
