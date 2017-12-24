package com.barbarum.tutorial.code;

import com.barbarum.tutorial.code.number.RomanToIntegerNumber;
import org.junit.Assert;
import org.junit.Test;

public class TestRomanToIntegerNumber extends BasicTestCase {

    @Test
    public void testRomanToInteger() throws Exception {
        testTemplate(1, "I");
        testTemplate(3, "III");

        testTemplate(4, "IV");
        testTemplate(5, "V");
        testTemplate(6, "VI");
        testTemplate(9, "IX");
        testTemplate(19, "XIX");
        testTemplate(29, "XXIX");
        testTemplate(39, "XXXIX");
        testTemplate(40, "XL");
        testTemplate(50, "L");
        testTemplate(90, "XC");
        testTemplate(99, "XCIX");

        testTemplate(100, "C");
        testTemplate(400, "CD");
        testTemplate(500, "D");
        testTemplate(900, "CM");
        testTemplate(1000, "M");
        testTemplate(3984, "MMMCMLXXXIV");
    }

    private void testTemplate(int expected, String roman) {
        Assert.assertEquals(expected, RomanToIntegerNumber.romanToInt(roman));
    }
}
