package LLD.trie;


public class TrieDriver {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
        System.out.println(trie.countWordsEqualTo("app"));   // returns 1
        System.out.println(trie.countWordsStartingWith("app"));   // returns 1
        
        
        trie.insert("apple");
        System.out.println(trie.search("apple"));     // returns true
        System.out.println(trie.countWordsEqualTo("apple"));   // returns 1
        System.out.println(trie.countWordsStartingWith("apple"));   // returns 1
        
        System.out.println(trie.countWordsEqualTo("app"));   // returns 1
        System.out.println(trie.countWordsStartingWith("app"));   // returns 2
    }
}
