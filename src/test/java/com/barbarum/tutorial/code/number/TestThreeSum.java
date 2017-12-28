package com.barbarum.tutorial.code.number;

import com.barbarum.tutorial.code.number.sum.ThreeSum;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestThreeSum {

    @Test
    public void testThreeSum() throws Exception {
        List<List<Integer>> result = Arrays.asList(Arrays.asList(-2, -1, 3), Arrays.asList(-2, 0, 2), Arrays.asList(-1, 0, 1));
        Assert.assertEquals(result, ThreeSum.threeSum(new int[]{3, 0, -2, -1, 1, 2}));


        result = Arrays.asList(
                Arrays.asList(-4, -2, 6),
                Arrays.asList(-2, -2, 4),
                Arrays.asList(-4, 0, 4),
                Arrays.asList(-4, 1, 3),
                Arrays.asList(-2, 0, 2),
                Arrays.asList(-4, 2, 2));

        Assert.assertEquals(result, ThreeSum.threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}));
    }

    @Test
    public void testThreeSumWithCursor() throws Exception {
        List<List<Integer>> result = Arrays.asList(Arrays.asList(-2, -1, 3), Arrays.asList(-2, 0, 2), Arrays.asList(-1, 0, 1));
        Assert.assertEquals(result, ThreeSum.effectiveThreeSum(new int[]{3, 0, -2, -1, 1, 2}));


        result = Arrays.asList(
                Arrays.asList(-4, -2, 6),
                Arrays.asList(-4, 0, 4),
                Arrays.asList(-2, -2, 4),
                Arrays.asList(-4, 1, 3),
                Arrays.asList(-4, 2, 2),
                Arrays.asList(-2, 0, 2));

        Assert.assertEquals(result, ThreeSum.effectiveThreeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}));
    }
}
