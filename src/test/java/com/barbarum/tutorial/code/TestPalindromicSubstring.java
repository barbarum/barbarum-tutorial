package com.barbarum.tutorial.code;

import org.junit.Assert;
import org.junit.Test;

public class TestPalindromicSubstring {

    @Test
    public void testLongestPalindrome() throws Exception {
        Assert.assertEquals("bab", PalindromicSubstring.longestPalindrome("babad"));
        Assert.assertEquals("bb", PalindromicSubstring.longestPalindrome("cbbd"));
    }
}