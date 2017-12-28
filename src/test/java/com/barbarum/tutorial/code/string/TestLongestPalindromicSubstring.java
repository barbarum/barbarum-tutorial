package com.barbarum.tutorial.code.string;

import com.barbarum.tutorial.code.string.LongestPalindromicSubstring;
import org.junit.Assert;
import org.junit.Test;

public class TestLongestPalindromicSubstring {

    @Test
    public void testLongestPalindrome() throws Exception {
        Assert.assertEquals("bab", LongestPalindromicSubstring.longestPalindrome("babad"));
        Assert.assertEquals("bb", LongestPalindromicSubstring.longestPalindrome("cbbd"));
    }
}
