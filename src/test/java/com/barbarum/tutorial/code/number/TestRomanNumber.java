package com.barbarum.tutorial.code.number;

import com.barbarum.tutorial.code.BasicTestCase;
import com.barbarum.tutorial.code.number.RomanNumber;
import org.junit.Assert;
import org.junit.Test;

public class TestRomanNumber extends BasicTestCase {

    @Test
    public void testIntToRoman() throws Exception {
        Assert.assertEquals("I", RomanNumber.intToRoman(1));
        Assert.assertEquals("III", RomanNumber.intToRoman(3));
        Assert.assertEquals("IV", RomanNumber.intToRoman(4));
        Assert.assertEquals("V", RomanNumber.intToRoman(5));
        Assert.assertEquals("VI", RomanNumber.intToRoman(6));
        Assert.assertEquals("IX", RomanNumber.intToRoman(9));
        Assert.assertEquals("XIX", RomanNumber.intToRoman(19));
        Assert.assertEquals("XXIX", RomanNumber.intToRoman(29));
        Assert.assertEquals("XXXIX", RomanNumber.intToRoman(39));
        Assert.assertEquals("XL", RomanNumber.intToRoman(40));
        Assert.assertEquals("L", RomanNumber.intToRoman(50));
        Assert.assertEquals("XC", RomanNumber.intToRoman(90));
        Assert.assertEquals("XCIX", RomanNumber.intToRoman(99));
        Assert.assertEquals("C", RomanNumber.intToRoman(100));
        Assert.assertEquals("CD", RomanNumber.intToRoman(400));
        Assert.assertEquals("D", RomanNumber.intToRoman(500));
        Assert.assertEquals("CM", RomanNumber.intToRoman(900));
        Assert.assertEquals("M", RomanNumber.intToRoman(1000));
        Assert.assertEquals("MMMCMLXXXIV", RomanNumber.intToRoman(3984));
    }
}
