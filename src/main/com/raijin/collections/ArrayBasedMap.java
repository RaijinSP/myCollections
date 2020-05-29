package com.raijin.collections;

import java.util.*;

/*My own arraybasedmap implementation as part of studying collections framework*/

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair> kv = new ArrayList<Pair>();
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        for (Pair p: kv) {
            if (key.equals(p.getKey())) return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Pair p: kv) {
            if (value.equals(p.getValue())) return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null) throw new NullPointerException();
        for (Pair p : kv) {
            if (key.equals(p.getKey())) return p.getValue();

        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) throw new NullPointerException();
        if (!this.containsKey(key)) {
            Pair p = new Pair(key, value);
            kv.add(p);
            size++;
            return null;
        }
        for (Pair p: kv) {
            if (key.equals(p.getKey())) {
                V val = p.getValue();
                p.setValue(value);
                return val;
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        if (key == null) throw new NullPointerException();
        for (Pair p: kv) {
            if (key.equals(p.getKey())) {
                V val = p.getValue();
                kv.remove(p);
                size--;
                return val;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry: m.entrySet())
            put(entry.getKey(), entry.getValue());
    }

    @Override
    public void clear() {
        kv = new ArrayList<>();
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<>();
        for (Pair p : kv) keys.add(p.getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<V>();
        for (Pair p : kv) values.add(p.getValue());
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(kv);
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            final V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) return false;
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }
    }
}
