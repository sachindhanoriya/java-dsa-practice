package LRU_cache;

public class SolutionSystem {
    public static void main(String[] args) {
        LRUCacheWithBuiltInDS cache = new LRUCacheWithBuiltInDS(4);
        cache.put(1,2);
        System.out.println(cache.get(1));
    }
}
