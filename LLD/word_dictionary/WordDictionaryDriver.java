package LLD.word_dictionary;

public class WordDictionaryDriver {
    public static void main(String[] args) {
        var wordDictionary = new WordDictionary();
        
        wordDictionary.addWord("at");
        System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");
        System.out.println(wordDictionary.search(".at"));
        
        // wordDictionary.addWord("bad");
        // wordDictionary.addWord("dad");
        // wordDictionary.addWord("mad");
        // System.out.println(wordDictionary.search("pad"));
        // System.out.println(wordDictionary.search("bad"));
        // System.out.println(wordDictionary.search(".ad"));
        // System.out.println(wordDictionary.search("b.."));
    }
}
