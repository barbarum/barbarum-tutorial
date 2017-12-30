package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Assert;
import org.junit.Test;

public class TestBinarySearchTree {

    @Test
    public void testSerialize() {
        Node<Integer> root = new Node<Integer>(5);
        root.setChildren(2, 7);
        root.getLeft().setChildren(1, 3);
        root.getRight().setChildren(6, 8);
        root.getLeft().getRight().setRight(4);
        Assert.assertArrayEquals(new Integer[]{1, 4, 3, 2, 6, 8, 7, 5}, BinarySearchTree.serialize(root));
    }

    @Test
    public void testDeserialize() {
        Integer items[] = new Integer[]{1, 4, 3, 2, 6, 8, 7, 5};
        Node<Integer> root = BinarySearchTree.deserialize(items);
        Assert.assertArrayEquals(items, BinarySearchTree.serialize(root));
    }
}
