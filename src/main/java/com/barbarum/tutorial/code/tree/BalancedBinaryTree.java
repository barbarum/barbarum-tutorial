package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.HashMap;
import java.util.Map;

public class BalancedBinaryTree {

    /**
     * Definition of height balanced tree - the height differences of any node's left and right subtree is not greater than 1.
     *
     * @param root the given tree.
     * @param <T>  generalized type.
     * @return true if the tree is balanced, otherwise false
     */

    public static <T> boolean examine(Node<T> root) {
        return examine(root, new HashMap<>());
    }

    private static <T> boolean examine(Node<T> root, Map<Node<T>, Integer> cache) {
        if (root == null) {
            return true;
        }

        int leftHeight = getHeight(root.getLeft(), cache);
        int rightHeight = getHeight(root.getRight(), cache);

        return Math.abs(leftHeight - rightHeight) <= 1
                && examine(root.getLeft(), cache)
                && examine(root.getRight(), cache);
    }

    private static <T> int getHeight(Node<T> root, Map<Node<T>, Integer> cache) {
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

        return convert(newHead, length);
    }

    private static Node<Integer> convert(Node<Integer> head, int length) {
        if (length == 0) {
            return null;
        }

        int mid = length / 2;

        Node<Integer> left = convert(head, mid);

        Node<Integer> root = head.getRight();
        head.setRight(root.getRight());

        Node<Integer> right = convert(head, length - (mid + 1));

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
