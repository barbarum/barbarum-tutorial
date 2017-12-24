package com.barbarum.tutorial.code;


import com.barbarum.tutorial.code.number.sum.TwoSum;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/two-sum/description/
 */
public class TestTwoSum {

    @Test
    public void testSumWithBasicAlgorithm() throws Exception {
        Assert.assertArrayEquals(new int[]{0, 1}, TwoSum.sumWithBasicAlgorithm(new int[]{2, 7, 11, 15}, 9));
        Assert.assertArrayEquals(new int[]{1, 2}, TwoSum.sumWithBasicAlgorithm(new int[]{3, 2, 4}, 6));
        Assert.assertArrayEquals(new int[]{0, 3}, TwoSum.sumWithBasicAlgorithm(new int[]{0, 4, 3, 0}, 0));
    }

    @Test
    public void testSumWithDiff() throws Exception {
        Assert.assertArrayEquals(new int[]{0, 1}, TwoSum.sumWithDiff(new int[]{2, 7, 11, 15}, 9));
        Assert.assertArrayEquals(new int[]{1, 2}, TwoSum.sumWithDiff(new int[]{3, 2, 4}, 6));
        Assert.assertArrayEquals(new int[]{0, 3}, TwoSum.sumWithDiff(new int[]{0, 4, 3, 0}, 0));
    }

    @Test
    public void testSumWithMap() throws Exception {
        Assert.assertArrayEquals(new int[]{0, 1}, TwoSum.sumWithMap(new int[]{2, 7, 11, 15}, 9));
        Assert.assertArrayEquals(new int[]{1, 2}, TwoSum.sumWithMap(new int[]{3, 2, 4}, 6));
        Assert.assertArrayEquals(new int[]{0, 3}, TwoSum.sumWithMap(new int[]{0, 4, 3, 0}, 0));
    }
}
