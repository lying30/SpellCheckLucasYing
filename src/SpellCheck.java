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

        String[] falseWords = new String[text.length];
        int placeInArray = 0;
        // binary search implementation
        for (String word: text){
            int low = 0;
            int high = dictionary.length-1;
            boolean found = false;
            while (low<=high) {
                int mid = (low + high) / 2;
                int compare = word.compareTo(dictionary[mid]);
                // returns 0 if it is the same
                if (compare == 0) {
                    found = true;
                    break;
                }
                // returns negative number if the word is to the left of the middle word in the dictionary
                else if (compare < 0) {
                    high = mid - 1;
                }
                // returns positive number if the word is to the right of the middle word in the dictionary
                else {
                    low = mid + 1;
                }

            }
            // check for duplicates
            if (!found){
                boolean alreadyInArray = false;
                for (int i = 0; i< placeInArray; i++){
                    if (falseWords[i].equals(word)){
                        alreadyInArray = true;
                        break;
                    }
                }
                if (!alreadyInArray) {
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
