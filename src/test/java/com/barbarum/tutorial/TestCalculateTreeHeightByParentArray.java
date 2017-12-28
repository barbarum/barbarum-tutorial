package com.barbarum.tutorial;

import com.barbarum.tutorial.code.tree.CalculateTreeHeightByParentArray;
import org.junit.Assert;
import org.junit.Test;

public class TestCalculateTreeHeightByParentArray {

    @Test
    public void testNormalCase() {
        Assert.assertEquals(3, CalculateTreeHeightByParentArray.calculateHeight(new int[]{3, 5, 0, -1, 5, 3, 0}));
        Assert.assertEquals(3, CalculateTreeHeightByParentArray.calculateHeight(new int[]{-1, 0, 1, 5, 1, 0}));
    }
}
