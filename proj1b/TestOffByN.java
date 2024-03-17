import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static Palindrome pal = new Palindrome();
    static CharacterComparator offBy3 = new OffByN(3);

    @Test
    public void tesOffBy3() {
        assertTrue(offBy3.equalChars('a', 'd'));
        assertTrue(offBy3.equalChars('d', 'a'));
        assertFalse(offBy3.equalChars('a', 'b'));
        assertFalse(offBy3.equalChars('a', 'a'));
    }

    @Test
    public void testPalindrome() {
        assertTrue(pal.isPalindrome("", offBy3));
        assertTrue(pal.isPalindrome("a", offBy3));
        assertTrue(pal.isPalindrome("ad", offBy3));
        assertFalse(pal.isPalindrome("ada", offBy3));
        assertTrue(pal.isPalindrome("adad", offBy3));
    }

}
