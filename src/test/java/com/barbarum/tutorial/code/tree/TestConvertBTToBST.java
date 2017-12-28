package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.BinaryTree;
import com.barbarum.tutorial.code.tree.ConvertBTToBST;
import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestConvertBTToBST {

    @Test
    public void testNormalCase() {
        Node<Integer> root = new Node<Integer>(0);
        root.setLeft(1).setLeft(3).setRight(6).setRight(8);
        root.getLeft().setRight(4);
        root.setRight(2).setLeft(5).setRight(7);

        ConvertBTToBST.convert(root);

        Assert.assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8), BinaryTree.traversalLDR(root));
    }
}
