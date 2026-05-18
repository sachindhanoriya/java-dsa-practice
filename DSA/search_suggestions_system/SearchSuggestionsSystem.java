package DSA.search_suggestions_system;

import java.util.*;

public class SearchSuggestionsSystem {
    public static void main(String[] args) {
        var tests = 4;
        var products = new String[][] {    
            { "havana" },
            { "mobile", "mouse", "moneypot", "monitor", "mousepad" },
            { "mobile", "moneypot", "monitor", "mouse", "mousepad" },
            { "havana" }
        };
        var searchWord = new String[] { "tatiana", "mouse", "mouse", "havana" };

        var sol = new Solution();
        for (int i = 0; i < tests; i++) {
            System.out.println(sol.suggestedProducts(products[i], searchWord[i]));
        }
    }
}

class TrieNode {
    private TrieNode[] letters;
    private boolean endOfWord;
    private char letter;

    public TrieNode() {
        letter = '$';
        letters = new TrieNode[26];
        endOfWord = false;
    }

    public TrieNode(char c) {
        letter = c;
        letters = new TrieNode[26];
        endOfWord = false;
    }

    public char getLetter() {
        return letter;
    }

    public TrieNode[] getLetters() {
        return letters;
    }

    public boolean getEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean flag) {
        endOfWord = flag;
    }

    public TrieNode setNextLetter(char c) {
        if (letters[c - 'a'] == null)
            letters[c - 'a'] = new TrieNode(c);
        return letters[c - 'a'];
    }

    public TrieNode getNextLetter(char c) {
        return letters[c - 'a'];
    }
}

class Trie {
    private TrieNode root;
    private int matchCount;

    public Trie() {
        root = new TrieNode();
        matchCount = 0;
    }

    public void insert(String str) {
        TrieNode node = root;
        for (char c : str.toCharArray()) {
            node = node.setNextLetter(c);
        }
        node.setEndOfWord(true);
    }

    public void getTop3MatchesInto(String matchStr, List<String> outputTop3) {
        TrieNode node = root;
        for (char c : matchStr.toCharArray()) {
            if (node == null)
                return;
            node = node.getNextLetter(c);
        }
        if (node == null)
            return;
        dfs(node, matchStr.substring(0, matchStr.length() - 1), outputTop3);
        matchCount = 0;
    }

    private void dfs(TrieNode node, String curStr, List<String> outputTop3) {
        if (matchCount == 3)
            return;

        curStr += node.getLetter();

        if (node.getEndOfWord()) {
            outputTop3.add(curStr);
            matchCount++;
        }

        for (TrieNode next : node.getLetters())
            if (next != null)
                dfs(next, curStr, outputTop3);

        return;
    }
}

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();
        List<List<String>> suggestions = new ArrayList<>();

        for (String product : products)
            trie.insert(product);

        for (int i = 1; i <= searchWord.length(); i++) {
            var top3 = new ArrayList<String>();
            String searchString = searchWord.substring(0, i);
            trie.getTop3MatchesInto(searchString, top3);
            suggestions.add(top3);
        }

        return suggestions;
    }
}