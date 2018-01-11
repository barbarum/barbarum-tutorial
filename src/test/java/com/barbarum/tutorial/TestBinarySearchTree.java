package com.barbarum.tutorial;

import com.barbarum.tutorial.code.tree.BinarySearchTree;
import com.barbarum.tutorial.code.tree.BinarySearchTreeRecover;
import com.barbarum.tutorial.code.tree.data.Node;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.barbarum.tutorial.code.tree.BinaryTree.traversalLDR;

public class TestBinarySearchTree {

    @Test
    public void testTraversalLDR() {
        Node<Integer> bst = BinarySearchTree.build(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19});
        List<Integer> list = traversalLDR(bst);
        System.out.println(list);
    }

    @Test
    public void testDoubleLinkedListConvector() {
        Node<Integer> bst = BinarySearchTree.build(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19});
        Node<Integer> result = BinarySearchTree.convert(bst);
        StringBuilder builder = new StringBuilder();

        builder.append("Forward -> ");
        Node<Integer> node = result;
        Node<Integer> nonNull = node;
        for (; node != null; node = node.getRight()) {
            builder.append(node.getData()).append(" ");
            nonNull = node;
        }

        builder.append("\n").append("Backward -> ");
        for (node = nonNull; node != null; node = node.getLeft()) {
            builder.append(node.getData()).append(" ");
        }

        System.out.println(builder.toString());
    }

    @Test
    public void testFloor() {
        Node<Integer> root = new Node<Integer>(6);
        root.setLeft(2).setChildren(0, 4);
        root.setRight(9).setLeft(8);
        Assert.assertEquals(Integer.valueOf(9),
                BinarySearchTree.floor(root, 10).orElseThrow(AssertionFailedError::new));
    }

    @Test
    public void testCeiling() {
        Node<Integer> root = new Node<Integer>(6);
        root.setLeft(2).setChildren(0, 4);
        root.setRight(9).setLeft(8);
        Assert.assertEquals(Integer.valueOf(4),
                BinarySearchTree.ceiling(root, 3).orElseThrow(AssertionFailedError::new));
    }

    @Test
    public void testTreeRecover(){
        Node<Integer> root = new Node<Integer>(6);
        root.setLeft(2).setChildren(0, 8);
        root.setRight(9).setLeft(4);

        BinarySearchTreeRecover.recover(root);
        Assert.assertEquals(Arrays.asList(0,2,4,6,8,9), BinarySearchTree.traversalLDR(root));
    }
}
