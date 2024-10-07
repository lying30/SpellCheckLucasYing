import java.util.ArrayList;

/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [Lucas Ying]
 * */

/*
class TrieNode {
    // Array of child nodes
    TrieNode[] children = new TrieNode[27];
    boolean isEndOfWord = false;
}

class Trie {
    //class
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert (String word){
        //start at the root
        TrieNode currentNode = root;
        // Start from loop and loop through each character of the word
        for (char c : word.toCharArray()) {
            // Calculate index for each character
            int index;
            if (c == '\'') {
                index = 26;
            }
            else if (c>= 'a' && c<='z') {
                index = (int) c - 'a';
            }
            else {
                continue;
            }
            // If child node exists then if not word = not found
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }
            // move to the child node and return true if it reaches the end of a valid word
            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfWord = true;

    }
    public boolean search (String word) {
        //start at the root
        TrieNode currentNode = root;
        // Start from loop and loop through each character of the word
        for (char c : word.toCharArray()) {
            // Calculate index for each character
            int index;
            if (c == '\'') {
                index = 26;
            }
            else if (c>= 'a' && c<='z') {
                index = (int) c - 'a';
            }
            else {
                continue;
            }
            // If child node exists then if not word = not found
            if (currentNode.children[index] == null) {
                return false;
            }
            // move to the child node and return true if it reaches the end of a valid word
            currentNode = currentNode.children[index];
        }
        return currentNode.isEndOfWord;
    }

}
public class SpellCheck {

//     checkWords finds all words in text that are not present in dictionary
//     @param text The list of all words in the text.
//     @param dictionary The list of all accepted words.
//     @return String[] of all mispelled words in the order they appear in text. No duplicates.

    public String[] checkWords(String[] text, String[] dictionary) {

        Trie dictionaryTrie = new Trie();
        Trie misspelledTrie = new Trie();

        ArrayList<String> falseWordsList = new ArrayList<>();

        for (String word: dictionary) {
            dictionaryTrie.insert(word.toLowerCase());
        }
        // Check each word in the text
        for (String word : text) {
            // Clean the word: lowercase and remove non-alphabetic characters except apostrophes
            String cleanedWord = word.toLowerCase();

            if (!dictionaryTrie.search(cleanedWord) && !misspelledTrie.search(cleanedWord) && !cleanedWord.isEmpty()) {
                misspelledTrie.insert(cleanedWord);
                System.out.println(cleanedWord);
                falseWordsList.add(cleanedWord);

            }
        }
        return falseWordsList.toArray(new String[0]);
    }
}
*/

class TSTNode {
    char value;
    TSTNode left, middle, right;
    boolean isEndOfWord;

    public TSTNode(char value) {
        this.value = value;
        this.isEndOfWord = false;
    }
}

class TST {
    private TSTNode root;

    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }

    private TSTNode insert(TSTNode node, char[] word, int index) {
        if (index>= word.length){
            return node;
        }

        char c = word[index];

        if (node == null) {
            node = new TSTNode(c);
        }

        if (c < node.value) {
            node.left = insert(node.left, word, index);
        } else if (c > node.value) {
            node.right = insert(node.right, word, index);
        } else {
            if (index + 1 < word.length) {
                node.middle = insert(node.middle, word, index + 1);
            } else {
                node.isEndOfWord = true;
            }
        }
        return node;
    }

    public boolean search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    private boolean search(TSTNode node, char[] word, int index) {
        if (node == null) {
            return false;
        }
        if (index>= word.length){
            return false;
        }
        char c = word[index];

        if (c < node.value) {
            return search(node.left, word, index);
        } else if (c > node.value) {
            return search(node.right, word, index);
        } else {
            if (index + 1 == word.length) {
                return node.isEndOfWord;
            } else {
                return search(node.middle, word, index + 1);
            }
        }
    }
}
public class SpellCheck {

//     checkWords finds all words in text that are not present in dictionary
//     @param text The list of all words in the text.
//     @param dictionary The list of all accepted words.
//     @return String[] of all misspelled words in the order they appear in text. No duplicates.

    public String[] checkWords(String[] text, String[] dictionary) {

        TST dictionaryTST = new TST();
        TST misspelledTST = new TST();
        ArrayList<String> falseWordsList = new ArrayList<>();

        for (String word: dictionary) {
            dictionaryTST.insert(word.toLowerCase());
        }
        // Check each word in the text
        for (String word : text) {
            // Clean the word: lowercase and remove non-alphabetic characters except apostrophes
            String cleanedWord = word.toLowerCase();

            if (!dictionaryTST.search(cleanedWord) && !misspelledTST.search(cleanedWord) && !cleanedWord.isEmpty()) {
                misspelledTST.insert(cleanedWord);
                System.out.println(cleanedWord);
                falseWordsList.add(cleanedWord);

            }
        }
        return falseWordsList.toArray(new String[0]);
    }
}
