package LLD.word_dictionary;


class WordDictionary {

    private class TrieNode {
        private TrieNode[] letters;
        private boolean endOfWord;

        public TrieNode() {
            letters = new TrieNode[26];
            endOfWord = false;
        }

        public TrieNode addLetter(char c) {
            if (letters[c - 'a'] == null)
                letters[c - 'a'] = new TrieNode();
            return letters[c - 'a'];
        }

        public void setEndOfWord() {
            endOfWord = true;
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.addLetter(c);
        }
        node.setEndOfWord();
    }

    public boolean search(String word) {
        return dfs(root, 0, word, word.length());
    }

    private boolean dfs(TrieNode node, int idx, String word, int n) {
        if (idx == n)
            return node.isEndOfWord();

        char curChar = word.charAt(idx);
        if (curChar == '.') {
            boolean isFound = false;
            for (int i = 0; i < node.letters.length; i++) {
                TrieNode next = node.letters[i];
                if (next != null)
                    isFound |= dfs(next, idx + 1, word, n);
            }
            return isFound;
        } else if (node.letters[curChar - 'a'] != null) {
            return dfs(node.letters[curChar - 'a'], idx + 1, word, n);
        }
        return false;
    }
}
