package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

public class LowestCommonAncestor {

    /**
     * Search LCA in binary tree.
     */
    public static Node<Integer> search(Node<Integer> root, Node<Integer> a, Node<Integer> b) {
        return ifPresent(root, a) && ifPresent(root, b) ? searchInternal(root, a, b) : null;
    }

    private static Node<Integer> searchInternal(Node<Integer> root, Node<Integer> a, Node<Integer> b) {
        if (root == null) {
            return null;
        }
        if (root == a || root == b) {
            return root;
        }

        Node<Integer> left = searchInternal(root.getLeft(), a, b);
        Node<Integer> right = searchInternal(root.getRight(), a, b);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    private static boolean ifPresent(Node<Integer> root, Node<Integer> node) {
        if (root == null) {
            return false;
        }
        return root == node
                || ifPresent(root.getLeft(), node)
                || ifPresent(root.getRight(), node);
    }
}
