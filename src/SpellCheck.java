import java.util.ArrayList;

/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 * Completed by: [Lucas Ying]
 * */

/*
class TrieNode {
    // Array of child nodes, one for each letter of the alphabet plus an apostrophe
    TrieNode[] children = new TrieNode[255];
    // Indicates if this node marks the end of a valid word
    boolean isEndOfWord = false;
}

class Trie {
    // Root node of the Trie
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert (String word){
        // Start at the root node
        TrieNode currentNode = root;
        // Loop through each character of the word
        for (char c : word.toCharArray()) {
            // Get ASCII value of the character
            int index = (int) c;

            // If child node does not exist, create it
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }

            // Move to the child node
            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfWord = true;
    }
    public boolean search (String word) {
        // Start at the root node
        TrieNode currentNode = root;
        // Loop through each character of the word
        for (char c : word.toCharArray()) {
            // Get ASCII value of the character
            int index = (int) c;

            // If child node does not exist, the word is not found
            if (currentNode.children[index] == null) {
                return false;
            }
            // Move to the child node
            currentNode = currentNode.children[index];
        }
        return currentNode.isEndOfWord;
    }
}
public class SpellCheck {

//     checkWords finds all words in text that are not present in dictionary
//     @param text The list of all words in the text.
//     @param dictionary The list of all accepted words.
//     @return String[] of all misspelled words in the order they appear in text. No duplicates.

    public String[] checkWords(String[] text, String[] dictionary) {

        // Initialize a trie for the dictionary and misspelled words
        Trie dictionaryTrie = new Trie();
        Trie misspelledTrie = new Trie();

        // List to hold misspelled words
        ArrayList<String> falseWordsList = new ArrayList<>();

        // Insert each word from the dictionary into the trie
        for (String word: dictionary) {
            dictionaryTrie.insert(word.toLowerCase());
        }

        // Check each word in the text
        for (String word : text) {
            // Convert to lowercase
            String cleanedWord = word.toLowerCase();

            // Check if the word is not in the dictionary and has not already been identified as misspelled
            if (!dictionaryTrie.search(cleanedWord) && !misspelledTrie.search(cleanedWord) && !cleanedWord.isEmpty()) {
                // Insert the misspelled word into the trie and add to the list of false words
                misspelledTrie.insert(cleanedWord);
                // For debugging --> to see misspelled words
                System.out.println(cleanedWord);
                falseWordsList.add(cleanedWord);
            }
        }
        // Return the list of misspelled words as an array
        return falseWordsList.toArray(new String[0]);
    }
}
*/

class TSTNode {
    // Value of node
    char value;
    // Pointers to 3 children
    TSTNode left, middle, right;
    // Indicates if this node marks the end of a valid word
    boolean isEndOfWord;

    public TSTNode(char value) {
        this.value = value;
        this.isEndOfWord = false;
    }
}

class TST {
    // Root node of the TST
    private TSTNode root;
    // Inserts a word into the TST
    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }
    // Recursive method to insert a word into the TST
    private TSTNode insert(TSTNode node, char[] word, int index) {
        // Base case --> if the index exceeds the word length, return the current node
        if (index>= word.length){
            return node;
        }

        // Get the character at the index
        char c = word[index];

        // Create a new node if it doesn't exist
        if (node == null) {
            node = new TSTNode(c);
        }

        // Traverse left if the character is less than the node value
        if (c < node.value) {
            node.left = insert(node.left, word, index);

        }
        // Traverse right if the character is greater than the node value
        else if (c > node.value) {
            node.right = insert(node.right, word, index);
        }
        // Traverse middle if the character is equal to the node value
        else {
            // If there are more characters to insert, continue down the middle
            if (index + 1 < word.length) {
                node.middle = insert(node.middle, word, index + 1);
            } else {
                node.isEndOfWord = true;
            }
        }
        return node;
    }

    // Searches for a word in the TST
    public boolean search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    // Recursive method to search for a word in the TST
    private boolean search(TSTNode node, char[] word, int index) {
        // If the current node is null, the word is not found
        if (node == null) {
            return false;
        }
        // Base case --> if the index exceeds the word length, return false
        if (index>= word.length){
            return false;
        }

        // Get the character at the index
        char c = word[index];

        // Traverse left if the character is less than the node value
        if (c < node.value) {
            return search(node.left, word, index);
        }
        // Traverse right if the character is greater than the node value
        else if (c > node.value) {
            return search(node.right, word, index);
        }
        // Traverse middle if the character is equal to the node value
        else {
            // Check if we have reached the end of the word
            if (index + 1 == word.length) {
                return node.isEndOfWord;
            } else {
                // Continue down the middle
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

        // Initialize a TST for the dictionary and one for misspelled words
        TST dictionaryTST = new TST();
        TST misspelledTST = new TST();

        // List to hold misspelled words
        ArrayList<String> falseWordsList = new ArrayList<>();

        // Insert each word from the dictionary into the TST
        for (String word: dictionary) {
            dictionaryTST.insert(word.toLowerCase());
        }

        // Check each word in the text
        for (String word : text) {
            // Clean the word: convert to lowercase
            String cleanedWord = word.toLowerCase();

            // Check id the word is not in the dictionary and has not already been identified as misspelled
            if (!dictionaryTST.search(cleanedWord) && !misspelledTST.search(cleanedWord) && !cleanedWord.isEmpty()) {
                // Insert the misspelled word into the TST and add to the list of false words
                misspelledTST.insert(cleanedWord);
                // For debugging --> to see misspelled words
                System.out.println(cleanedWord);
                falseWordsList.add(cleanedWord);

            }
        }
        return falseWordsList.toArray(new String[0]);
    }
}
