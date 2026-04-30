package LLD.trie;


public class Trie {

    private class TrieNode {
        private TrieNode[] letters;
        private boolean endOfWord;
        private int wordCount;

        public TrieNode() {
            letters = new TrieNode[26];
            endOfWord = false;
            wordCount = 0;
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

        public void endThisWord() {
            endOfWord = true;
        }

        public void eraseThisWord() {
            if (wordCount == 0)
                endOfWord = false;
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }

        public void incWordCount() {
            wordCount++;
        }
        
        public void decWordCount() {
            wordCount--;
        }

        public int getWordCount() {
            return wordCount;
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
            node.incWordCount();
        }
        node.endThisWord();
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node == null)
                return false;
            node = node.get(c);
        }
        return node != null && node.isEndOfWord();
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c: prefix.toCharArray()) {
            if (node == null)
                return false;
            node = node.get(c);
        }
        return node != null && node.getWordCount() > 0;
    }

    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node == null)
                return 0;
            node = node.get(c);
        }
        if (node == null || !node.isEndOfWord())
            return 0;
        return node.getWordCount();
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
        return node.getWordCount();
    }
    
    public void erase(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node == null)
                return;
            node = node.get(c);
            node.decWordCount();
        }
        node.eraseThisWord();
    }

}
