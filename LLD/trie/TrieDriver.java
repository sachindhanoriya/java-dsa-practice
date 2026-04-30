package LLD.trie;


public class TrieDriver {
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("b")); // returns false
        trie.insert("apple");
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.countWordsEqualTo("apple"));   // returns 2
        System.out.println(trie.countWordsStartingWith("app"));   // returns 2
        trie.erase("apple");
        System.out.println(trie.countWordsEqualTo("apple"));   // returns 1
        System.out.println(trie.countWordsStartingWith("app"));   // returns 1
        trie.erase("apple");
        System.out.println(trie.countWordsEqualTo("apple"));   // returns 0
        System.out.println(trie.countWordsStartingWith("app"));   // returns 0
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns false
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}
