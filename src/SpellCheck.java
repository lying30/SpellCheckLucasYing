
/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [Lucas Ying]
 * */
class TrieNode {
    //26 letters plus apostrohpe
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


    /**
     * checkWords finds all words in text that are not present in dictionary
     *
     * @param text The list of all words in the text.
     * @param dictionary The list of all accepted words.
     * @return String[] of all mispelled words in the order they appear in text. No duplicates.
     */
    public String[] checkWords(String[] text, String[] dictionary) {

        Trie trie = new Trie();
        String[] falseWords = new String[text.length];
        int placeInArray = 0;

        for (String word: dictionary) {
            trie.insert(word.toLowerCase());
        }
        // Check each word in the text
        for (String word : text) {
            // Clean the word: lowercase and remove non-alphabetic characters except apostrophes
            String cleanedWord = word.toLowerCase().replaceAll("[^a-z']", "");

            if (!trie.search(cleanedWord) && !cleanedWord.isEmpty()) {
                // Check for duplicates before adding
                boolean alreadyInArray = false;
                for (int i = 0; i < placeInArray; i++) {
                    if (falseWords[i].equals(cleanedWord)) {
                        alreadyInArray = true;
                        break;
                    }
                }
                if (!alreadyInArray){
                    falseWords[placeInArray] = cleanedWord;
                    placeInArray++;
                }
            }
        }
        String[] result = new String[placeInArray];
        // Copy over the found misspelled words
        System.arraycopy(falseWords, 0, result, 0, placeInArray);
        return result;
    }
}


class TSTNode {
    char value;

}

class TST {

}