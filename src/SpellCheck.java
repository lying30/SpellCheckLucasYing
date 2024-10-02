import static java.lang.StringUTF16.charAt;

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

public class Trie{
    //class
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert (String word){
        //write method

        //start at the root
        TrieNode currentNode = root;
        // Start from loop and loop through each character of the word
        for (String letter : word) {
            // calculate index for each character and check if the corresponding child node exists
            char character = (char) letter.indexOf(0);
            int index = (int) character - 'a';
            if (currentNode.children[index] != null) {

            }
            // if not then create a new node
            // move to the child node and mark the end of the word
        }

    }
    public boolean search (String word) {
        //write method

        //start at the root
        TrieNode currentNode = root;
        // Start from loop and loop through each character of the word
        for (String letter : word) {
            char character = (char) letter.indexOf(0);
            // Calculate index for each character
            int index = (int) character - 'a';
            if (currentNode.children[index] != null) {
                currentNode = currentNode.children[index];
                if (currentNode.isEndOfWord) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        // check if child node exists then if not word = not found
        // move to the child node and return true if it reaches the end of a valid word
        return false;
    }

    public class TST {

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
            trie.insert(word);
        }
        // Check each word in the text
        for (String word : text) {
            if (!trie.search(word)) {
                // Check for duplicates before adding
                boolean alreadyInArray = false;
                for (int i = 0; i < placeInArray; i++) {
                    if (falseWords[i].equals(word)) {
                        alreadyInArray = true;
                        break;
                    }
                }
                if (!alreadyInArray){
                    falseWords[placeInArray] = word;
                    placeInArray++;
                }
            }
        }
        String[] result = new String[placeInArray];
        System.arraycopy(falseWords, 0, result, 0, placeInArray);
        return result;
    }
}
