package com.barbarum.tutorial.code;

import org.junit.Assert;
import org.junit.Test;

public class TestMedianOfSortedArrays {

    @Test
    public void testFindMedianSortedArrays() throws Exception {
        double delta = 0.00001;
        Assert.assertEquals(2.0, MedianOfSortedArrays.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), delta);
        Assert.assertEquals(2.5, MedianOfSortedArrays.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), delta);
    }

    @Test
    public void testFindMedianSortedArraysWithCursor() throws Exception {
        double delta = 0.00001;
        Assert.assertEquals(2.0, MedianOfSortedArrays.findMedianSortedArraysWithCursor(new int[]{1, 3}, new int[]{2}), delta);
        Assert.assertEquals(2.5, MedianOfSortedArrays.findMedianSortedArraysWithCursor(new int[]{1, 2}, new int[]{3, 4}), delta);
    }
}
