package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.DiagonalSum;
import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestDiagonalSumOfBinaryTree {

    @Test
    public void testDiagonalSum() {
        Node<Integer> root = new Node<Integer>(0);
        root.setChildren(1, 2);
        root.getLeft().setChildren(4, 5);
        root.getRight().setChildren(3, 6);
        root.getLeft().getRight().setLeft(7);

        Assert.assertEquals(Arrays.asList(8, 9, 11), DiagonalSum.sum(root));

    }
}
