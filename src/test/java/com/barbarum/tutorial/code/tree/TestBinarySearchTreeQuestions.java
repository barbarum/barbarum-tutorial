package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;
import com.barbarum.tutorial.util.PrintUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TestBinarySearchTreeQuestions {

    private Data data = new Data();

    @Test
    public void testDelete() {
        BinarySearchTreeQuestions.delete(this.data.standardBST, 4);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 5, 6, 7), BinarySearchTree.traversalLDR(this.data.standardBST));
        BinarySearchTreeQuestions.delete(this.data.standardBST, 8);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 5, 6, 7), BinarySearchTree.traversalLDR(this.data.standardBST));
    }

    @Test
    public void testIdenticalToBuildBST() {
        int[] tree1 = new int[]{3, 5, 4, 6, 1, 0, 2};
        int[] tree2 = new int[]{3, 1, 5, 2, 4, 6, 0};

        PrintUtil.println("Tree 1 -> ", tree1);
        PrintUtil.println("Tree 2 -> ", tree2);
        assertTrue(BinarySearchTreeQuestions.identical(tree1, tree2));
    }

    @Test
    public void testIdenticalToBuildBST2() {
        int[] tree1 = new int[]{6, 9, 8};
        int[] tree2 = new int[]{6, 8, 9};

        PrintUtil.println("Tree 1 -> ", tree1);
        PrintUtil.println("Tree 2 -> ", tree2);
        assertFalse(BinarySearchTreeQuestions.identical(tree1, tree2));
    }

    @Test
    public void testDoubleLinkedListConverter() {
        Node<Integer> head = new Node<Integer>(1);
        head.setRight(2).setRight(3).setRight(4).setRight(5);

        for (Node<Integer> node = head; node != null; node = node.getRight()) {
            if (node.getRight() != null) {
                node.getRight().setLeft(node);
            }
        }
        Node<Integer> tree = BalancedBinaryTree.convert(head);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5), BinarySearchTree.traversalLDR(tree));
        Assert.assertNull(BalancedBinaryTree.convert(null));
    }

    @Test
    public void testHasOnlyOneChildren() {
        PrintUtil.println(new int[]{20, 10, 11, 13, 12}, BinarySearchTreeQuestions::hasOnlyOneChildren);
        Assert.assertTrue(BinarySearchTreeQuestions.hasOnlyOneChildren(new int[]{20, 10, 11, 13, 12}));
    }

    @Test
    public void testBuildBST() {
        Node<Integer> root = BinarySearchTreeQuestions.buildBST(this.data.standardBSTArray);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), BinarySearchTree.traversalLDR(root));
    }

    @Test
    public void testCompleteTree() {
        assertTrue(BinarySearchTreeQuestions.isComplete(data.completeBST1));
        assertTrue(BinarySearchTreeQuestions.isComplete(data.completeBST2));
        assertFalse(BinarySearchTreeQuestions.isComplete(data.inCompleteBST1));
        assertFalse(BinarySearchTreeQuestions.isComplete(data.inCompleteBST2));
    }

    @Test
    public void testRangeOut() {
        Node<Integer> root = new Node<Integer>(8);
        root.setChildren(5, 11);
        root.getLeft().setChildren(2, 7);
        root.getRight().setChildren(9, 12);
        root.getLeft().getRight().setLeft(6);
        root.getRight().getLeft().setRight(10);
        root.getRight().getRight().setRight(13);

        BinarySearchTreeQuestions.remove(root, 3, 9);
        Assert.assertEquals(Arrays.asList(5, 6, 7, 8, 9), BinarySearchTree.traversalLDR(root));
    }

    @Test
    public void testCountBST() {
        Assert.assertEquals(5, BinarySearchTreeQuestions.countTrees(3));
        Assert.assertEquals(42, BinarySearchTreeQuestions.countTrees(5));
    }

    private static class Data {
        Node<Integer> standardBST;
        int[] standardBSTArray;
        Node<Integer> completeBST1;
        Node<Integer> completeBST2;

        Node<Integer> inCompleteBST1;
        Node<Integer> inCompleteBST2;

        {
            // standard BST
            standardBST = new Node<Integer>(4);
            standardBST.setChildren(2, 6);
            standardBST.getLeft().setChildren(1, 3);
            standardBST.getRight().setChildren(5, 7);

            // standard BST Array
            standardBSTArray = new int[]{1, 2, 3, 4, 5, 6, 7};

            // Complete BST
            completeBST1 = standardBST;

            completeBST2 = new Node<Integer>(4);
            completeBST2.setChildren(2, 6);
            completeBST2.getLeft().setChildren(1, 3);
            completeBST2.getRight().setLeft(5);

            inCompleteBST1 = new Node<Integer>(4);
            inCompleteBST1.setChildren(2, 6);
            inCompleteBST1.getLeft().setChildren(1, 3);
            inCompleteBST1.getRight().setRight(5);

            inCompleteBST2 = new Node<Integer>(1);
            inCompleteBST2.setChildren(2, 3);
            inCompleteBST2.getLeft().setChildren(4, 5);
            inCompleteBST2.getRight().setLeft(6);
            inCompleteBST2.getLeft().getLeft().setLeft(7);
        }
    }

}
