package LLD.LRU_cache;

import java.util.*;

class Node<K, V> {
    Node<K, V> next, prev;
    K key;
    V value;

    public Node(K key, V value) {
        next = prev = null;
        this.key = key;
        this.value = value;
    }
}

public class LRUCacheCustomDS<K, V> {
    
    int size;
    int cap;
    Map<K, Node<K, V>> cachedItems;
    Node<K, V> head, tail;

    public LRUCacheCustomDS(int capacity) {
        cap = capacity;
        size = 0;
        cachedItems = new HashMap<>();
        head = new Node<>(null, null);
        tail = new Node<>(null, null);

        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        Node<K, V> node = cachedItems.get(key);

        removeNode(node);

        addNode(node);

        return node.value;
    }

    public void put(K key, V value) {
        if (cachedItems.containsKey(key)) {
            Node<K, V> evictNode = cachedItems.remove(key);
            
            removeNode(evictNode);
            
            size--;
        } else if (size == cap) {
            Node<K, V> evictNode = head.next;
            cachedItems.remove(evictNode.key);

            removeNode(evictNode);
            
            size--;
        }

        Node<K, V> node = new Node<>(key, value);

        addNode(node);

        cachedItems.put(key, node);
        size++;
    }

    private void addNode(Node<K, V> node) {
        node.prev = tail.prev;
        node.next = tail;
        
        tail.prev.next = node;
        tail.prev = node;
    }

    private Node<K, V> removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}
