package com.barbarum.tutorial.code;

import com.barbarum.tutorial.code.number.CplusplusAtoi;
import org.junit.Assert;
import org.junit.Test;

public class TestCplusplusAtoi extends BasicTestCase {

    @Test
    public void testMyAtoi() throws Exception {
        Assert.assertEquals(2, CplusplusAtoi.myAtoi("2"));
        Assert.assertEquals(23, CplusplusAtoi.myAtoi("23"));
        Assert.assertEquals(23, CplusplusAtoi.myAtoi("  23"));
        Assert.assertEquals(23, CplusplusAtoi.myAtoi(" 23"));
        Assert.assertEquals(23, CplusplusAtoi.myAtoi("+23"));
        Assert.assertEquals(-23, CplusplusAtoi.myAtoi("-23"));
        Assert.assertEquals(0, CplusplusAtoi.myAtoi("a"));
        Assert.assertEquals(0, CplusplusAtoi.myAtoi("+"));
        Assert.assertEquals(0, CplusplusAtoi.myAtoi("-"));
        Assert.assertEquals(0, CplusplusAtoi.myAtoi("ab"));
        Assert.assertEquals(0, CplusplusAtoi.myAtoi("-0"));
    }

    @Test
    public void testEasyWay() throws Exception {
        Assert.assertEquals(2, CplusplusAtoi.easyWay("2"));
        Assert.assertEquals(23, CplusplusAtoi.easyWay("23"));
        Assert.assertEquals(23, CplusplusAtoi.easyWay("  23"));
        Assert.assertEquals(23, CplusplusAtoi.easyWay(" 23"));
        Assert.assertEquals(23, CplusplusAtoi.easyWay("+23"));
        Assert.assertEquals(-23, CplusplusAtoi.easyWay("-23"));
        Assert.assertEquals(0, CplusplusAtoi.easyWay("a"));
        Assert.assertEquals(0, CplusplusAtoi.easyWay("+"));
        Assert.assertEquals(0, CplusplusAtoi.easyWay("-"));
        Assert.assertEquals(0, CplusplusAtoi.easyWay("ab"));
        Assert.assertEquals(0, CplusplusAtoi.easyWay("-0"));
    }
}
