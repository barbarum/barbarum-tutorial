package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.BasicTestCase;
import com.barbarum.tutorial.code.tree.data.Node;
import com.barbarum.tutorial.util.PrintUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;

import static com.barbarum.tutorial.code.tree.BinaryTree.isCousin;
import static com.barbarum.tutorial.code.tree.BinaryTreeQuestions.removeNodesOnPathSumLessThanK;

public class TestBinaryTreeQuestions extends BasicTestCase {

    public void testIsCousin() {
        Node<Integer> root = BinarySearchTree.build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertTrue(isCousin(root, root.getLeft().getLeft(), root.getRight().getRight()));
        assertFalse(isCousin(root, root.getLeft().getLeft(), root.getLeft().getRight()));
    }

    public void testRemoveNodesOnPathSumLessThanK() {
        Node<Integer> root = BinarySearchTree.build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        removeNodesOnPathSumLessThanK(root, 10);
        System.out.println(BinaryTree.traversalLDR(root));
        Assert.assertArrayEquals(new Object[]{4, 5, 6, 7}, BinaryTree.traversalLDR(root).toArray());
    }

    public void testSymmetricTree() {
        Node<Integer> root = new Node<Integer>(3);
        root.setLeft(1).setLeft(0).setLeft(3);
        root.getLeft().setRight(2).setRight(4);
        root.setRight(1).setLeft(2).setLeft(4);
        root.getRight().setRight(0).setRight(3);

        assertTrue(BinaryTreeQuestions.symmetric(root));
    }

    public void testSymmetricTreeNotMatch() {
        Node<Integer> root = new Node<Integer>(3);
        root.setLeft(1).setLeft(0).setLeft(3);
        root.getLeft().setRight(2).setRight(4);
        root.setRight(1).setLeft(0).setLeft(3);
        root.getRight().setRight(2).setRight(4);

        assertFalse(BinaryTreeQuestions.symmetric(root));
    }

    public void testBottomView() {
        Node<Integer> root = new Node<Integer>(1);
        root.setChildren(2, 3);
        root.getLeft().setChildren(4, 5);
        root.getRight().setChildren(6, 7);

        PrintUtil.println("Output - > ", BinaryTreeView.bottom(root));
    }

    public void testBuildBSTWithArrays() {
        int inOrder[] = new int[]{4, 2, 5, 1, 6, 3, 7};
        int preOrder[] = new int[]{4, 5, 2, 6, 7, 3, 1};

        Node<Integer> root = BinaryTreeQuestions.buildBST(inOrder, preOrder);

        Assert.assertEquals(Arrays.asList(4, 2, 5, 1, 6, 3, 7), BinaryTree.traversalLDR(root));
        Assert.assertEquals(Arrays.asList(4, 5, 2, 6, 7, 3, 1), BinaryTree.traversalLRD(root, new ArrayList<>()));
    }

    public void testBuildBSTWithParents() {
        int[] parents = new int[]{3, 5, 0, -1, 5, 3, 0};
        Node<Integer> root = BinaryTreeQuestions.buildBST(parents);
        Assert.assertEquals(Arrays.asList(2, 0, 6, 3, 1, 5, 4), BinaryTree.traversalLDR(root));

        parents = new int[]{-1, 0, 1, 5, 1, 0};
        root = BinaryTreeQuestions.buildBST(parents);
        Assert.assertEquals(Arrays.asList(2, 1, 4, 0, 3, 5), BinaryTree.traversalLDR(root));
    }

    public void testNodesWithoutSiblings() {
        Node<Integer> root = new Node<Integer>(0);
        root.setLeft(1).setLeft(3).setRight(6).setRight(8);
        root.getLeft().setRight(4);
        root.setRight(2).setLeft(5).setRight(7);

        Assert.assertEquals(Arrays.asList(6, 8, 5, 7), BinaryTreeQuestions.collectSingleChildNodes(root));
    }

    public void testRemoveHalfNodes() {
        Node<Integer> root = new Node<Integer>(0);
        root.setLeft(1).setLeft(3).setRight(6).setRight(8);
        root.getLeft().setRight(4);
        root.setRight(2).setLeft(5).setRight(7);

        BinaryTreeQuestions.removeHalfNodes(root);
        Assert.assertEquals(Arrays.asList(8, 1, 4, 0, 7), BinaryTree.traversalLDR(root));
    }


    public void testLargestBSTSize() {
        Node<Integer> testcaseA = new Node<Integer>(10);
        testcaseA.setChildren(15, 1);
        testcaseA.getLeft().setChildren(7, 16);
        testcaseA.getRight().setChildren(9, 14);
        Assert.assertEquals(3, BinaryTreeQuestions.findLargestBSTSize(testcaseA));

        Node<Integer> testcaseB = new Node<Integer>(10);
        testcaseB.setChildren(6, 12);
        testcaseB.getLeft().setChildren(7, 4);
        testcaseB.getRight().setChildren(9, 14);
        testcaseB.getRight().getRight().setChildren(13, 16);
        Assert.assertEquals(5, BinaryTreeQuestions.findLargestBSTSize(testcaseB));
    }
}
