package com.barbarum.tutorial.code;

import com.barbarum.tutorial.code.tree.BinarySearchTree;
import com.barbarum.tutorial.code.tree.BinaryTree;
import com.barbarum.tutorial.code.tree.BinaryTreeQuestions;
import com.barbarum.tutorial.code.tree.Node;
import org.junit.Assert;

import static com.barbarum.tutorial.code.tree.BinaryTreeQuestions.isCousin;
import static com.barbarum.tutorial.code.tree.BinaryTreeQuestions.removeNodesOnPathSumLessThanK;

public class TestBinaryTreeQuestions extends BasicTestCase {

    public void testIsCousin() {
        Node<Integer> root = BinarySearchTree.buildBST(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertTrue(isCousin(root, root.getLeft().getLeft(), root.getRight().getRight()));
        assertFalse(isCousin(root, root.getLeft().getLeft(), root.getLeft().getRight()));
    }

    public void testRemoveNodesOnPathSumLessThanK() {
        Node<Integer> root = BinarySearchTree.buildBST(new Integer[]{1, 2, 3, 4, 5, 6, 7});
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

        assertTrue(BinaryTreeQuestions.symmetricTree(root));
    }

    public void testSymmetricTreeNotMatch() {
        Node<Integer> root = new Node<Integer>(3);
        root.setLeft(1).setLeft(0).setLeft(3);
        root.getLeft().setRight(2).setRight(4);
        root.setRight(1).setLeft(0).setLeft(3);
        root.getRight().setRight(2).setRight(4);

        assertFalse(BinaryTreeQuestions.symmetricTree(root));
    }

    public void testDoubleLinkedListConverter() {
        Node<Integer> root = new Node<Integer>(1);

        root.setChildren(2, 3);
        root.getLeft().setChildren(4, 5);
        root.getRight().setChildren(6, 7);

        Node<Integer> head = BinaryTreeQuestions.convertToDoubleLinkedList(root);
        Node<Integer> tail = head;

        int[] forwardActual = new int[7];
        int[] backwardActual = new int[7];
        int index = -1;
        for (Node<Integer> node = head; node != null; node = node.getRight()) {
            forwardActual[++index] = node.getData();
            tail = node;
        }
        for (Node<Integer> node = tail; node != null; node = node.getLeft()) {
            backwardActual[index--] = node.getData();
        }

        Assert.assertArrayEquals(new int[]{4, 2, 5, 1, 6, 3, 7}, forwardActual);
        Assert.assertArrayEquals(new int[]{4, 2, 5, 1, 6, 3, 7}, backwardActual);
    }
}
