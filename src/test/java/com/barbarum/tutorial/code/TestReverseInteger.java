package com.barbarum.tutorial.code;

import org.junit.Assert;
import org.junit.Test;

public class TestReverseInteger extends BasicTestCase {

    @Test
    public void testReverse() throws Exception {
        Assert.assertEquals(321, ReverseInteger.reverse(123));
        Assert.assertEquals(-123, ReverseInteger.reverse(-321));
    }

    @Test
    public void testReverseWithNum() throws Exception {
        Assert.assertEquals(321, ReverseInteger.reverseWithNum(123));
        Assert.assertEquals(-123, ReverseInteger.reverseWithNum(-321));
    }
}
