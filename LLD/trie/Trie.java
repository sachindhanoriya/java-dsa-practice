package LLD.trie;


public class Trie {

    private class TrieNode {
        private TrieNode[] letters;
        private int wordEndCount;
        private int wordPrefixCount;

        public TrieNode() {
            letters = new TrieNode[26];
            wordEndCount = 0;
            wordPrefixCount = 0;
        }

        public TrieNode put(char c) {
            int letterIdx = c - 'a';
            if (letters[letterIdx] == null)
                letters[letterIdx] = new TrieNode();
            return letters[letterIdx];
        }

        public TrieNode get(char c) {
            return letters[c - 'a'];
        }

        public void endWord() {
            wordEndCount++;
        }

        public void eraseWord() {
            wordEndCount--;
        }

        public int getWordEndCount() {
            return wordEndCount;
        }

        public void incWordPrefixCount() {
            wordPrefixCount++;
        }
        
        public void decWordPrefixCount() {
            wordPrefixCount--;
        }

        public int getWordPrefixCount() {
            return wordPrefixCount;
        }

    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {   // apple
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            node = node.put(c);
            node.incWordPrefixCount();
        }
        node.endWord();
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node == null)
                return false;
            node = node.get(c);
        }
        return node != null && node.getWordEndCount() > 0;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c: prefix.toCharArray()) {
            if (node == null)
                return false;
            node = node.get(c);
        }
        return node != null && node.getWordPrefixCount() > 0;
    }

    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node == null)
                return 0;
            node = node.get(c);
        }
        if (node == null || node.getWordEndCount() == 0)
            return 0;
        return node.getWordPrefixCount();
    }
    
    public int countWordsStartingWith(String prefix) {
        TrieNode node = root;
        for (char c: prefix.toCharArray()) {
            if (node == null)
                return 0;
            node = node.get(c);
        }
        if (node == null)
            return 0;
        return node.getWordPrefixCount();
    }
    
    public void erase(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node == null)
                return;
            node = node.get(c);
            node.decWordPrefixCount();
        }
        node.eraseWord();
    }

}
