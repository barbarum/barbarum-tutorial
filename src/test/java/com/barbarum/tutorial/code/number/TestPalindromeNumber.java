package com.barbarum.tutorial.code.number;

import com.barbarum.tutorial.code.BasicTestCase;
import com.barbarum.tutorial.code.number.PalindromeNumber;
import org.junit.Assert;
import org.junit.Test;

public class TestPalindromeNumber extends BasicTestCase {

    @Test
    public void testIsPalindrome() throws Exception {
        Assert.assertEquals(false, PalindromeNumber.isPalindrome(-11));
        Assert.assertEquals(false, PalindromeNumber.isPalindrome(-1));
        Assert.assertEquals(true, PalindromeNumber.isPalindrome(0));
        Assert.assertEquals(true, PalindromeNumber.isPalindrome(11));
        Assert.assertEquals(false, PalindromeNumber.isPalindrome(10));
        Assert.assertEquals(true, PalindromeNumber.isPalindrome(121));
    }
}
