package com.barbarum.tutorial.code.string;

import com.barbarum.tutorial.code.BasicTestCase;
import com.barbarum.tutorial.code.string.LongestCommonPrefix;
import org.junit.Assert;
import org.junit.Test;

public class TestLongestCommonPrefix extends BasicTestCase {

    @Test
    public void testLongestCommonPrefix() throws Exception {
        Assert.assertEquals("c", LongestCommonPrefix.longestCommonPrefix(new String[]{"c", "c"}));
        Assert.assertEquals("", LongestCommonPrefix.longestCommonPrefix(new String[]{"a", "a", "c"}));
    }
}
