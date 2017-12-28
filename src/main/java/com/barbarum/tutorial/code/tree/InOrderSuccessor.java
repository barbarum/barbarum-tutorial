package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

public class InOrderSuccessor {

    public static Node<Integer> search(Node<Integer> node) {
        if (node == null) {
            return null;
        }
        return node.getRight() != null ? leftMostDescendant(node.getRight()) : firstLeftAncestor(node);
    }

    private static Node<Integer> firstLeftAncestor(Node<Integer> root) {
        for (Node<Integer> node = root, parent = root.getParent(); parent != null; node = parent, parent = parent.getParent()) {
            if (node == parent.getLeft()) {
                return parent;
            }
        }
        return null;
    }

    private static Node<Integer> leftMostDescendant(Node<Integer> root) {
        Node<Integer> result = root;
        for (Node<Integer> node = root.getLeft(); node != null; node = node.getLeft()) {
            result = node;
        }
        return result;
    }

    public static Node<Integer> search(Node<Integer> root, Node<Integer> node) {
        if (root == null || node == null) {
            return null;
        }
        return node.getRight() != null ? leftMostDescendant(node.getRight()) : inOrderTraversal(root, node, null);
    }

    private static Node<Integer> inOrderTraversal(Node<Integer> root, Node<Integer> node, Node<Integer> nearestLeftAncestor) {
        if (root == null) {
            return null;
        }
        Node<Integer> result = inOrderTraversal(root.getLeft(), node, root);
        if (result != null) {
            return result;
        }
        if (root == node) {
            return nearestLeftAncestor;
        }
        return inOrderTraversal(root.getRight(), node, nearestLeftAncestor);
    }
}
