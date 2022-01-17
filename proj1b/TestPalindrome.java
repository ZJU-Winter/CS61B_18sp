import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    static CharacterComparator cc = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome(" "));
        assertFalse(palindrome.isPalindrome("AB"));
        assertTrue(palindrome.isPalindrome("  "));
        assertTrue(palindrome.isPalindrome("RaceCar"));
        assertTrue(palindrome.isPalindrome("NOon"));
        assertFalse(palindrome.isPalindrome("JoshHug"));
    }

    @Test
    public void testOverloadedIsPalindrome() {
        assertTrue(palindrome.isPalindrome(" ", cc));
        assertTrue(palindrome.isPalindrome("AB", cc));
        assertTrue(palindrome.isPalindrome("ACb", cc));
        assertTrue(palindrome.isPalindrome("rACbq", cc));
        assertTrue(palindrome.isPalindrome("%ACb&", cc));
        assertFalse(palindrome.isPalindrome("AbDCE", cc));
    }
}
