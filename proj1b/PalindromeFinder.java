/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        CharacterComparator offByOne = new OffByOne();

        for (int i = 0; i < 26; i++) {
            CharacterComparator offByN = new OffByN(i);
            int theLongest = 0;
            in = new In("../library-sp18/data/words.txt");
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, offByN)) {
                    theLongest = Math.max(theLongest, word.length());
                }
            }
            System.out.println("when n is " + i + ", the longest word length is " + theLongest);
        }
    }
}
