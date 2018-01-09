package com.barbarum.tutorial.code.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLongestNonRepeatingCharacterSubstring {

    @Test
    public void testLengthOfLongestSubstring() {
        assertEquals(3, LongestNonRepeatingCharacterSubstring.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, LongestNonRepeatingCharacterSubstring.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, LongestNonRepeatingCharacterSubstring.lengthOfLongestSubstring("pwwkew"));
        assertEquals(1, LongestNonRepeatingCharacterSubstring.lengthOfLongestSubstring("c"));
        assertEquals(2, LongestNonRepeatingCharacterSubstring.lengthByHashMap("au"));
        assertEquals(2, LongestNonRepeatingCharacterSubstring.lengthByHashMap("cbb"));
    }

    @Test
    public void testLengthByHashMap() {
        assertEquals(3, LongestNonRepeatingCharacterSubstring.lengthByHashMap("abcabcbb"));
        assertEquals(1, LongestNonRepeatingCharacterSubstring.lengthByHashMap("bbbbb"));
        assertEquals(3, LongestNonRepeatingCharacterSubstring.lengthByHashMap("pwwkew"));
        assertEquals(1, LongestNonRepeatingCharacterSubstring.lengthByHashMap("c"));
        assertEquals(2, LongestNonRepeatingCharacterSubstring.lengthByHashMap("au"));
        assertEquals(2, LongestNonRepeatingCharacterSubstring.lengthByHashMap("cbb"));
    }


    @Test
    public void testLengthByArrayAndASCII() {
        assertEquals(3, LongestNonRepeatingCharacterSubstring.lengthByArrayAndASCII("abcabcbb"));
        assertEquals(1, LongestNonRepeatingCharacterSubstring.lengthByArrayAndASCII("bbbbb"));
        assertEquals(3, LongestNonRepeatingCharacterSubstring.lengthByArrayAndASCII("pwwkew"));
        assertEquals(1, LongestNonRepeatingCharacterSubstring.lengthByArrayAndASCII("c"));
        assertEquals(2, LongestNonRepeatingCharacterSubstring.lengthByArrayAndASCII("au"));
        assertEquals(2, LongestNonRepeatingCharacterSubstring.lengthByArrayAndASCII("cbb"));
    }

    @Test
    public void testLengthByMaximumRange() {
        assertEquals(3, LongestNonRepeatingCharacterSubstring.lengthByMaximumRange("abcabcbb"));
        assertEquals(1, LongestNonRepeatingCharacterSubstring.lengthByMaximumRange("bbbbb"));
        assertEquals(3, LongestNonRepeatingCharacterSubstring.lengthByMaximumRange("pwwkew"));
        assertEquals(1, LongestNonRepeatingCharacterSubstring.lengthByMaximumRange("c"));
        assertEquals(2, LongestNonRepeatingCharacterSubstring.lengthByMaximumRange("au"));
        assertEquals(2, LongestNonRepeatingCharacterSubstring.lengthByMaximumRange("cbb"));
    }
}
