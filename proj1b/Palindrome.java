public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> out = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            out.addLast(word.charAt(i));
        }
        return out;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        if (wordDeque.isEmpty() || wordDeque.size() == 1) {
            return true;
        }
        if (wordDeque.removeFirst() == wordDeque.removeLast()) {
            String rec = "";
            for (int i = 0; i < wordDeque.size(); i++) {
                rec += wordDeque.get(i);
            }
            return isPalindrome(rec);
        } else {
            return false;
        }
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        if (wordDeque.isEmpty() || wordDeque.size() == 1) {
            return true;
        }
        if (cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
            String rec = "";
            for (int i = 0; i < wordDeque.size(); i++) {
                rec += wordDeque.get(i);
            }
            return isPalindrome(rec, cc);
        } else {
            return false;
        }
    }
}
