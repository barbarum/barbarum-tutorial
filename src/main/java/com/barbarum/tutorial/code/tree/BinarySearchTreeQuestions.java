package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeQuestions {

    public static Node<Integer> delete(Node<Integer> root, int key) {
        if (root == null) {
            return null;
        }

        int diff = root.getData().compareTo(key);
        if (diff > 0) {
            root.setLeft(delete(root.getLeft(), key));
            return root;
        }
        if (diff < 0) {
            root.setRight(delete(root.getRight(), key));
            return root;
        }

        return performDelete(root);
    }

    private static Node<Integer> performDelete(Node<Integer> root) {
        if (BinaryTree.isLeaf(root)) {
            return null;
        }
        if (BinaryTree.hasOnlyOneChild(root)) {
            return root.getLeft() != null ? root.getLeft() : root.getRight();
        }
        int nextGreaterKey = minimum(root.getRight());
        root.setData(nextGreaterKey);
        root.setRight(delete(root.getRight(), nextGreaterKey));
        return root;
    }

    private static int minimum(Node<Integer> root) {
        Node<Integer> target = root;
        for (Node<Integer> node = root; node != null; node = node.getLeft()) {
            target = node;
        }
        return target.getData();
    }

    /**
     * Given Pre-order traversal of a BST, examine if each non-leaf node has only one child. Assume that the BST contains unique entries.
     * Input: pre[] = {20, 10, 11, 13, 12}
     * Output: Yes
     * The give array represents following BST. In the following BST, every internal
     * node has exactly 1 child. Therefor, the output is true.
     */
    public static boolean hasOnlyOneChildren(int[] tree) {
        if (tree == null || tree.length <= 2) {
            return true;
        }
        for (int i = tree.length - 3; i >= 0; i--) {
            if (tree[i] < tree[i + 1] && tree[i] > tree[i + 2]) {
                return false;
            }
        }
        return true;
    }

    /**
     * http://www.ideserve.co.in/learn/check-if-identical-binary-search-trees-without-building-them-set-1
     * Given two arrays which would be used to construct two different binary match trees(BSTs), write a program to identify if the BSTs constructed from these would be identical. The condition is that the program should be able to identify this without actually building BSTs.
     */
    public static boolean identical(int[] tree1, int[] tree2) {
        if (tree1 == null || tree2 == null) {
            return tree1 == tree2;
        }
        if (tree1.length != tree2.length) {
            return false;
        }
        if (tree1.length == 0) {
            return true;
        }
        return identical(tree1, tree2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean identical(int[] tree1, int[] tree2, int tree1Root, int tree2Root, int minimum, int maximum) {
        if (tree1Root == -1 || tree2Root == -1) {
            return tree1Root == tree2Root;
        }
        if (tree1[tree1Root] != tree2[tree2Root]) {
            return false;
        }

        int tree1Left = findNodeIndex(tree1, tree1Root + 1, minimum, tree1[tree1Root]);
        int tree2Left = findNodeIndex(tree2, tree2Root + 1, minimum, tree2[tree2Root]);

        int tree1Right = findNodeIndex(tree1, tree1Root + 1, tree1[tree1Root], maximum);
        int tree2Right = findNodeIndex(tree2, tree2Root + 1, tree2[tree2Root], maximum);

        int rootData = tree1[tree1Root];

        return identical(tree1, tree2, tree1Left, tree2Left, minimum, rootData)
                && identical(tree1, tree2, tree1Right, tree2Right, rootData, maximum);
    }

    private static int findNodeIndex(int[] tree, int start, int minimum, int maximum) {
        for (int i = start; i < tree.length; i++) {
            if (isRangeOf(tree[i], minimum, maximum)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isRangeOf(int target, int minimum, int maximum) {
        return target > minimum && target < maximum;
    }

    public static Node<Integer> remove(Node<Integer> root, int low, int high) {
        if (root == null) {
            return null;
        }

        root.setLeft(remove(root.getLeft(), low, high));
        root.setRight(remove(root.getRight(), low, high));

        if (root.getData() < low) {
            return root.getRight();
        }
        if (root.getData() > high) {
            return root.getLeft();
        }
        return root;
    }
}
