package com.barbarum.tutorial.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static com.barbarum.tutorial.leetcode.pojo.ListNode.as;

public class TestAddTwoNumbers {

    @Test
    public void testAddTwoNumbers() {
        Assert.assertEquals("708", AddTwoNumers.addTwoNumbers(as(2, 4, 3), as(5, 6, 4)).convertToString());
        Assert.assertEquals("73", AddTwoNumers.addTwoNumbers(as(0), as(7, 3)).convertToString());


        Assert.assertEquals("708", AddTwoNumers.addTwoNumbers(as(2, 4, 3), as(5, 6, 4)).convertToString());
    }

    @Test
    public void testAddWithNewList() {
        Assert.assertEquals("708", AddTwoNumers.addWithNewList(as(2, 4, 3), as(5, 6, 4)).convertToString());
        Assert.assertEquals("73", AddTwoNumers.addWithNewList(as(0), as(7, 3)).convertToString());
    }
}
