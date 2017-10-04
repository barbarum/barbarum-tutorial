package com.barbarum.tutorial.hacker;

import com.barbarum.tutorial.code.BasicTestCase;
import org.junit.Assert;
import org.junit.Test;


public class TestArrayLeftRotation extends BasicTestCase {

    @Test
    public void testLeftRotationOperation() throws Exception {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 0}, ArrayLeftRotation.doLeftRotation(new int[]{0, 1, 2, 3, 4}, 1));
    }
}
