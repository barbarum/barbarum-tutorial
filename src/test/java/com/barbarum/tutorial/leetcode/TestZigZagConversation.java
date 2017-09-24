package com.barbarum.tutorial.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class TestZigZagConversation extends BasicTestCase {


    @Test
    public void testConvert() {
        Assert.assertEquals("PAHNAPLSIIGYIR", ZigZagConversion.convert("PAYPALISHIRING", 3));
    }

    @Test
    public void testConvertWithSB() {
        Assert.assertEquals("PAHNAPLSIIGYIR", ZigZagConversion.convertWithSB("PAYPALISHIRING", 3));
    }
}
