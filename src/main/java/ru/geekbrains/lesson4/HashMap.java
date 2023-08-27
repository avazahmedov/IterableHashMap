package ru.geekbrains.  lesson4;

import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.Iterator;

public class HashMap<K, V> implements Iterable<HashMap.Entity> {

    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5;
    private int size;

    // Массив бакетов (связных списков)
    private Bucket[] buckets;


    @Override
    public String toString() {
        //TODO: Если тяжело, пойти через toString
        return super.toString();
    }

    @Override
    public Iterator<HashMap.Entity> iterator() {
        return new HashMapIterator();
    }
    public static int i = 0;


    class HashMapIterator implements Iterator<HashMap.Entity> {
        Bucket<K, V> bucket = buckets[0];
        Bucket.Node node1 = bucket.head;

        @Override
        public boolean hasNext() {
            if(i +1 < buckets.length) return true;
            return false;
        }

        @Override
        public Entity next() {
            Entity entity1;
            Entity entity2 = new Entity();
                if(node1.next == null) {
                    if ((i+1) < buckets.length && buckets[i+1] != null ) {
                        i++;
                        bucket = buckets[i];
                        node1 = bucket.head;
                        entity1 = node1.value;
                        return entity1;
                    } else if ((i+1) < buckets.length && buckets[i+1] == null ){
                        i++;
                        return entity2;
                    }
                }
                else {
                    entity1 = node1.value;
                    node1 = node1.next;
                    return entity1;
                }
                return entity2;
        }

    }


    /**
     * Элемент хэш-таблицы
     */
    class Entity {

        // Ключ элемента
        K key;

        // Значение элемента
        V value;

        @Override
        public String toString() {
            return String.format("%s - %s", key, value);
        }
    }

    /**
     * Связный список
     *
     * @param <K> Ключ элемента хэш-таблицы
     * @param <V> Значение элемента хэш-таблицы
     */
    class Bucket<K, V> {

        // Указатель на первый элемент связного списка
        Node head;

        /**
         * Узел бакета (связного списка)
         */
        class Node {

            // Указатель на следующий элемент связного списка
            Node next;

            // Значение узла, указывающее на элемент хэш-таблицы
            Entity value;

            Node node;
        }

        /**
         * Добавление нового элемента хэш-таблицы
         *
         * @param entity элемент
         * @return
         */
        public V add(Entity entity) {
            Node node = new Node();
            node.value = entity;

            if (head == null) {
                head = node;
                return null;
            }

            Node currentNode = head;
            while (true) {
                if (currentNode.value.key.equals(entity.key)) {
                    V buf = (V) currentNode.value.value;
                    currentNode.value.value = entity.value;
                    return buf;
                }
                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                } else {
                    currentNode.next = node;
                    return null;
                }
            }
        }

        public V get(K key) {
            Node node = head;
            while (node != null) {
                if (node.value.key.equals(key))
                    return (V) node.value.value;
                node = node.next;
            }
            return null;
        }

        public V remove(K key) {
            if (head == null)
                return null;
            if (head.value.key.equals(key)) {
                V buf = (V) head.value.value;
                head = head.next;
                return buf;
            } else {
                Node node = head;
                while (node.next != null) {
                    if (node.next.value.key.equals(key)) {
                        V buf = (V) node.next.value.value;
                        node.next = node.next.next;
                        return buf;
                    }
                    node = node.next;
                }
                return null;
            }
        }

    }


    private int calculateBucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void recalculate() {
        size = 0;
        Bucket<K, V>[] old = buckets;
        buckets = new Bucket[old.length * 2];
        for (int i = 0; i < old.length; i++) {
            Bucket<K, V> bucket = old[i];
            if (bucket != null) {
                Bucket.Node node = bucket.head;
                while (node != null) {
                    put((K) node.value.key, (V) node.value.value);
                    node = node.next;
                }
            }
        }
    }

    public V put(K key, V value) {
        if (buckets.length * LOAD_FACTOR <= size) {
            recalculate();
        }
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            bucket = new Bucket();
            buckets[index] = bucket;
        }

        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;

        V buf = (V) bucket.add(entity);
        if (buf == null) {
            size++;
        }
        return buf;
    }

    public V get(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null)
            return null;
        return (V) bucket.get(key);
    }

    public V remove(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null)
            return null;
        V buf = (V) bucket.remove(key);
        if (buf != null) {
            size--;
        }
        return buf;
    }

    public HashMap() {
        buckets = new Bucket[INIT_BUCKET_COUNT];
    }

    public HashMap(int initCount) {
        buckets = new Bucket[initCount];
    }
}




