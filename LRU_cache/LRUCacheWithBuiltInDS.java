package LRU_cache;

import java.util.*;

public class LRUCacheWithBuiltInDS<K, V> {
    private LinkedHashMap<K, V> cachedItems;

    public LRUCacheWithBuiltInDS(int capacity) {
        cachedItems = new LinkedHashMap<>(15, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public <T> V get(K key) {
        return cachedItems.get(key);
    }
    
    public void put(K key, V value) {
        cachedItems.put(key, value);
    }
}
