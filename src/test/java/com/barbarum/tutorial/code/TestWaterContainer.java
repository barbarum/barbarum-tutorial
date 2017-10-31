package com.barbarum.tutorial.code;

import com.barbarum.tutorial.code.sum.WaterContainer;
import org.junit.Assert;
import org.junit.Test;

public class TestWaterContainer extends BasicTestCase {

    @Test
    public void testMaxArea() throws Exception {
        Assert.assertEquals(1, WaterContainer.maxArea(new int[]{1, 1}));
    }
}
