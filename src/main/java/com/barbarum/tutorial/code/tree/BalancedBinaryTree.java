package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.HashMap;
import java.util.Map;

public class BalancedBinaryTree {

    /**
     * Check if the given tree is balanced.
     */
    public static boolean examine(Node<Integer> root) {
        return examine(root, new HashMap<>());
    }

    private static boolean examine(Node<Integer> root, Map<Node<Integer>, Integer> cache) {
        if (root == null) {
            return true;
        }

        int leftHeight = getHeight(root.getLeft(), cache);
        int rightHeight = getHeight(root.getRight(), cache);

        return Math.abs(leftHeight - rightHeight) <= 1
                && examine(root.getLeft(), cache)
                && examine(root.getRight(), cache);
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

    /**
     * Convert a double linked list to balanced binary search tree.
     *
     * @param head the given head.
     * @return the root of the built tree.
     */
    public static Node<Integer> convert(Node<Integer> head) {
        int length = getLength(head);

        Node<Integer> newHead = new Node<Integer>(null);
        newHead.setRight(head);

        return inOrderTraversal(newHead, length);
    }

    private static Node<Integer> inOrderTraversal(Node<Integer> head, int length) {
        if (length == 0) {
            return null;
        }

        int mid = length / 2;

        Node<Integer> left = inOrderTraversal(head, mid);

        Node<Integer> root = head.getRight();
        head.setRight(root.getRight());

        Node<Integer> right = inOrderTraversal(head, length - mid - 1);

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

    public static void main(String args[]) {
        Node<Integer> root = new Node<Integer>(0);

        root.setChildren(1, 2);
        root.getLeft().setChildren(3, 4);
        root.getRight().setLeft(5);

        root.getLeft().getLeft().setRight(1);

        System.out.println(examine(root));
    }
}
