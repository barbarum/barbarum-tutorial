package com.barbarum.tutorial.code.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {

    private int capacity;

    private Entity<K, V> head;
    private Entity<K, V> tail;

    private Map<Long, Entity<K, V>> cache;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    public void put(K key, V value) {
        Entity<K, V> entity = cache.get(hash(key));

        if (entity != null) {
            entity.setValue(value);
            removeEntity(entity);
        } else if ((entity = add(key, value)) == null) {
            return;
        }

        setHead(entity);
    }

    public V get(K key) {
        Entity<K, V> entity = cache.get(hash(key));
        if (entity == null) {
            return null;
        }

        removeEntity(entity);
        setHead(entity);

        return entity.getValue();
    }

    public void delete(K key) {
        Entity<K, V> entity = cache.remove(hash(key));
        if (entity == null) {
            return;
        }
        removeEntity(entity);
    }


    private Entity<K, V> add(K key, V value) {
        if (cache.size() == capacity) {
            if (tail == null) {
                return null;
            } else {
                delete(tail.getKey());
            }
        }

        Entity<K, V> entity = new Entity<>(key, value);
        cache.put(hash(key), entity);
        return entity;
    }

    private void setHead(Entity<K, V> entity) {
        entity.setPrevious(null);
        entity.setNext(head);
        if (head != null) {
            head.setPrevious(entity);
        }
        head = entity;
        if (tail == null) {
            tail = head;
        }
    }

    private void removeEntity(Entity<K, V> entity) {
        if (entity.previous() != null) {
            entity.previous().setNext(entity.next());
        }else{
            // If the given entity is the head.
            head = entity.next();
        }

        // If the given entity is the tail.
        if (entity == tail) {
            tail = entity.previous();
        }
    }

    private long hash(K key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private static class Entity<K, V> {
        private K key;
        private V value;
        private Entity<K, V> previousEntity;
        private Entity<K, V> nextEntity;

        public Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entity<K, V> previous() {
            return previousEntity;
        }

        public void setPrevious(Entity<K, V> previous) {
            this.previousEntity = previous;
        }

        public Entity<K, V> next() {
            return nextEntity;
        }

        public void setNext(Entity<K, V> next) {
            this.nextEntity = next;
        }
    }

    private void println() {
        StringBuilder builder = new StringBuilder("[");
        for (Entity entity = head; entity != null; entity = entity.next()) {
            builder.append(entity.getKey()).append("=").append(entity.getValue()).append(",");
        }
        int length;
        if ((length = builder.length()) > 1) {
            builder.deleteCharAt(length - 1);
        }

        builder.append("]");
        System.out.println(builder.toString());
    }

    public static void main(String args[]) {
        LRUCache<Integer, Integer> cache = new LRUCache<Integer, Integer>(3);
        cache.put(1, 1);
        cache.println();

        cache.put(2, 2);
        cache.println();

        cache.put(3, 3);
        cache.println();

        cache.put(4, 4);
        cache.println();

        cache.put(1, 1);
        cache.println();

    }
}
