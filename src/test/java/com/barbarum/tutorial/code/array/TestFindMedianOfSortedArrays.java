package com.barbarum.tutorial.code.array;

import org.junit.Assert;
import org.junit.Test;

public class TestFindMedianOfSortedArrays {

    @Test
    public void testFindMedianSortedArrays() throws Exception {
        double delta = 0.00001;
        Assert.assertEquals(2.0, FindMedianOfSortedArrays.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), delta);
        Assert.assertEquals(2.5, FindMedianOfSortedArrays.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), delta);
    }

    @Test
    public void testFindMedianSortedArraysWithCursor() throws Exception {
        double delta = 0.00001;
        Assert.assertEquals(2.0, FindMedianOfSortedArrays.findMedianSortedArraysWithCursor(new int[]{1, 3}, new int[]{2}), delta);
        Assert.assertEquals(2.5, FindMedianOfSortedArrays.findMedianSortedArraysWithCursor(new int[]{1, 2}, new int[]{3, 4}), delta);
    }

    @Test
    public void testFindMedianSortedArraysV2() {
        double delta = 0.00001;
        Assert.assertEquals(2.0, FindMedianOfSortedArraysV2.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), delta);
        Assert.assertEquals(2.5, FindMedianOfSortedArraysV2.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), delta);
    }

}
