import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertFalse(offByOne.equalChars('x', 'x'));
        assertTrue(offByOne.equalChars('x', 'y'));
        assertTrue(offByOne.equalChars('y', 'x'));
        assertTrue(offByOne.equalChars('X', 'Y'));
        assertTrue(offByOne.equalChars('&', '%'));

    }
}
