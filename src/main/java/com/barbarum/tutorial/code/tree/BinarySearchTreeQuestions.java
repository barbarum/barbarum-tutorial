package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.*;

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

        return replaceNewRoot(root);
    }

    private static Node<Integer> replaceNewRoot(Node<Integer> root) {
        if (isLeaf(root)) {
            return null;
        }
        if (root.getLeft() == null) {
            return root.getRight();
        }
        if (root.getRight() == null) {
            return root.getLeft();
        }

        int key = findMinimumKey(root.getRight());
        delete(root, key);
        root.setData(key);
        return root;
    }

    private static int findMinimumKey(Node<Integer> root) {
        Node<Integer> node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getData();
    }

    private static boolean isLeaf(Node<Integer> node) {
        return node.getRight() == null && node.getLeft() == null;
    }

    /**
     * Given Pre-order traversal of a BST, check if each non-leaf node has only one child. Assume that the BST contains unique entries.
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

        int tree1Left = findNode(tree1, minimum, tree1[tree1Root], tree1Root + 1);
        int tree2Left = findNode(tree2, minimum, tree2[tree2Root], tree2Root + 1);

        int tree1Right = findNode(tree1, tree1[tree1Root], maximum, tree1Root + 1);
        int tree2Right = findNode(tree2, tree2[tree2Root], maximum, tree2Root + 1);

        return identical(tree1, tree2, tree1Left, tree2Left, minimum, tree1[tree1Root])
                && identical(tree1, tree2, tree1Right, tree2Right, tree1[tree1Root], maximum);
    }

    public static Node<Integer> convertDoubleLinkedListToBST(Node<Integer> head) {
        int length = getLength(head);

        Node<Integer> newHead = new Node<Integer>(null);
        newHead.setRight(head);

        return traversal(newHead, length);
    }

    private static Node<Integer> traversal(Node<Integer> head, int length) {
        if (length == 0) {
            return null;
        }

        int mid = length / 2;

        Node<Integer> left = traversal(head, mid);

        Node<Integer> root = head.getRight();
        head.setRight(root.getRight());

        Node<Integer> right = traversal(head, length - mid - 1);

        root.setLeft(left);
        root.setRight(right);

        return root;
    }

    private static int getLength(Node<Integer> head) {
        int length = 0;
        for (Node<Integer> node = head; node != null; node = node.getRight()) {
            length++;
        }
        return length;
    }

    private static int findNode(int[] tree, int minimum, int maximum, int index) {
        for (int i = index; i < tree.length; i++) {
            if (tree[i] > minimum && tree[i] < maximum) {
                return i;
            }
        }
        return -1;
    }

    public static Node<Integer> buildBST(int[] items) {
        if (items == null || items.length == 0) {
            return null;
        }
        return buildBST(items, 0, items.length - 1);
    }

    private static Node<Integer> buildBST(int[] items, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end + 1) / 2;

        Node<Integer> left = buildBST(items, start, mid - 1);
        Node<Integer> root = new Node<Integer>(items[mid]);
        Node<Integer> right = buildBST(items, mid + 1, end);

        root.setLeft(left);
        root.setRight(right);

        return root;
    }

    public static boolean isComplete(Node<Integer> root) {
        if (root == null) {
            return true;
        }

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);

        boolean previousNotComplete = false;

        while (!queue.isEmpty()) {
            Node<Integer> node = queue.poll();
            addNodeIfNotNull(queue, node.getLeft());
            addNodeIfNotNull(queue, node.getRight());

            if (node.getLeft() == null && node.getRight() != null) {
                return false;
            }
            if (previousNotComplete && !isLeaf(node)) {
                return false;
            }
            if (!previousNotComplete && !isFull(node)) {
                previousNotComplete = true;
            }
        }
        return true;
    }

    private static void addNodeIfNotNull(Queue<Node<Integer>> queue, Node<Integer> node) {
        if (node != null) {
            queue.add(node);
        }
    }

    private static boolean isFull(Node<Integer> node) {
        return node.getLeft() != null && node.getRight() != null;
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

    public static int countTrees(int n) {
        if (n < 0) {
            return 0;
        }
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;

        for (int length = 2; length <= n; length++) {
            for (int root = 0; root < length; root++) {
                cache[length] += cache[root] * cache[length - 1 - root];
            }
        }

        return cache[n];
    }
}
