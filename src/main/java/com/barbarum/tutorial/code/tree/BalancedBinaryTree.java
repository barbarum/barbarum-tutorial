package com.barbarum.tutorial.code.tree;

import java.util.HashMap;
import java.util.Map;

public class BalancedBinaryTree {

    /**
     * Check if the given tree is balanced.
     */
    public static boolean check(Node<Integer> root) {
        return check(root, new HashMap<>());
    }

    private static boolean check(Node<Integer> root, Map<Node<Integer>, Integer> cache) {
        if (root == null) {
            return true;
        }

        int leftHeight = getHeight(root.getLeft(), cache);
        int rightHeight = getHeight(root.getRight(), cache);

        return Math.abs(leftHeight - rightHeight) <= 1
                && check(root.getLeft(), cache)
                && check(root.getRight(), cache);
    }

    private static int getHeight(Node<Integer> root, Map<Node<Integer>, Integer> cache) {
        if (root == null) {
            return 0;
        }
        if (cache.containsKey(root)) {
            return cache.get(root);
        }
        int height = 1 + Math.max(getHeight(root.getLeft(), cache), getHeight(root.getRight(), cache));
        cache.put(root, height);
        return height;
    }

    public static void main(String args[]) {
        Node<Integer> root = new Node<Integer>(0);

        root.setChildren(1, 2);
        root.getLeft().setChildren(3, 4);
        root.getRight().setLeft(5);

        root.getLeft().getLeft().setRight(1);

        System.out.println(check(root));
    }
}
