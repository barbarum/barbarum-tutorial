package com.barbarum.tutorial.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLongestSubstring {

    @Test
    public void testLengthOfLongestSubstring() {
        assertEquals(3, LongestSubstring.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, LongestSubstring.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, LongestSubstring.lengthOfLongestSubstring("pwwkew"));
        assertEquals(1, LongestSubstring.lengthOfLongestSubstring("c"));
        assertEquals(2, LongestSubstring.lengthByHashMap("au"));
        assertEquals(2, LongestSubstring.lengthByHashMap("cbb"));
    }

    @Test
    public void testLengthByHashMap() {
        assertEquals(3, LongestSubstring.lengthByHashMap("abcabcbb"));
        assertEquals(1, LongestSubstring.lengthByHashMap("bbbbb"));
        assertEquals(3, LongestSubstring.lengthByHashMap("pwwkew"));
        assertEquals(1, LongestSubstring.lengthByHashMap("c"));
        assertEquals(2, LongestSubstring.lengthByHashMap("au"));
        assertEquals(2, LongestSubstring.lengthByHashMap("cbb"));
    }


    @Test
    public void testLengthByArrayAndASCII() {
        assertEquals(3, LongestSubstring.lengthByArrayAndASCII("abcabcbb"));
        assertEquals(1, LongestSubstring.lengthByArrayAndASCII("bbbbb"));
        assertEquals(3, LongestSubstring.lengthByArrayAndASCII("pwwkew"));
        assertEquals(1, LongestSubstring.lengthByArrayAndASCII("c"));
        assertEquals(2, LongestSubstring.lengthByArrayAndASCII("au"));
        assertEquals(2, LongestSubstring.lengthByArrayAndASCII("cbb"));
    }

    @Test
    public void testLengthByMaximumRange() {
        assertEquals(3, LongestSubstring.lengthByMaximumRange("abcabcbb"));
        assertEquals(1, LongestSubstring.lengthByMaximumRange("bbbbb"));
        assertEquals(3, LongestSubstring.lengthByMaximumRange("pwwkew"));
        assertEquals(1, LongestSubstring.lengthByMaximumRange("c"));
        assertEquals(2, LongestSubstring.lengthByMaximumRange("au"));
        assertEquals(2, LongestSubstring.lengthByMaximumRange("cbb"));
    }
}
