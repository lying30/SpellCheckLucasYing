/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [Lucas Ying]
 * */

public class SpellCheck {


    /**
     * checkWords finds all words in text that are not present in dictionary
     *
     * @param text The list of all words in the text.
     * @param dictionary The list of all accepted words.
     * @return String[] of all mispelled words in the order they appear in text. No duplicates.
     */
    public String[] checkWords(String[] text, String[] dictionary) {



        // Go through all the text one by one using binary search

        String[] falseWords = new String[text.length];
        int placeInArray = 0;
        for (String word: text){
            int lengthOfLargestWord = 0;
            for (String word2: text){
                if (word2.length()> lengthOfLargestWord){
                    lengthOfLargestWord = word2.length();
                }
            }
            // Edge case: If the word is larger than the largest word in the dictionary
            if (word.length() > lengthOfLargestWord) {
                falseWords[placeInArray] = word;
                placeInArray++;
            }
            for (String dict: dictionary) {
                if (word.equals(dict)){
                    break;
                }
                else {
                    falseWords[placeInArray] = word;
                    placeInArray++;
                }
            }


        }

        return falseWords;

        // How to do binary search?
        // For each of the words in the text, check the dictionary for if that string matches a string within the dictionary
        // If the word is found within the dictionary, continue with the next word in the text
        // If the word is not in the dictionary, add it to the array of falseWords that holds the array we will return at the end of our code
        // After we have gone through each word in our text, return falseWords and end the code
    }
}
