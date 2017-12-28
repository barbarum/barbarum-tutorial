package com.barbarum.tutorial;

import com.barbarum.tutorial.code.tree.LeftLeavesSum;
import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Assert;
import org.junit.Test;

public class TestLeftLeavesSum {

    @Test
    public void testNormalCase() {
        Node<Integer> root = new Node<Integer>(1);
        root.setLeft(2).setLeft(4).setRight(8).setLeft(9);
        root.getLeft().setRight(5);
        root.setRight(3).setChildren(6, 7);

        Assert.assertEquals(15, LeftLeavesSum.sum(root));
    }
}
