package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

public class AVLTree<T extends Comparable<T>> {

    private AVLNode<T> root;

    public AVLTree() {
        // Do nothing, empty constructor
    }

    public AVLTree(AVLNode<T> root) {
        this.root = root;
    }

    public void insert(T data) {
        this.root = insert(root, data);
    }

    private AVLNode<T> insert(AVLNode<T> root, T data) {
        if (root == null) {
            return new AVLNode<T>(data);
        }
        int diff = data.compareTo(root.getData());

        if (diff < 0) {
            root.setLeft(insert((AVLNode<T>) root.getLeft(), data));
        } else if (diff > 0) {
            root.setRight(insert((AVLNode<T>) root.getRight(), data));
        } else {
            return root;
        }

        calculateHeight(root);
        int balanceWeight = getBalanceWeight(root);

        // Balanced
        if (Math.abs(balanceWeight) <= 1) {
            return root;
        }

        // left-left case
        if (balanceWeight > 1 && data.compareTo(root.getLeft().getData()) < 0) {
            return rightRotate(root);
        }
        // right-right case
        if (balanceWeight < -1 && data.compareTo(root.getRight().getData()) > 0) {
            return leftRotate(root);
        }
        // left-right case
        if (balanceWeight > 1 && data.compareTo(root.getLeft().getData()) > 0) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        // right-left case
        if (balanceWeight < -1 && data.compareTo(root.getRight().getData()) < 0) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }

        return root;
    }

    public void delete(T data) {
        this.root = delete(this.root, data);
    }

    private AVLNode<T> delete(AVLNode<T> root, T data) {
        if (root == null) {
            return null;
        }

        int diff = data.compareTo(root.getData());

        if (diff < 0) {
            root.setLeft(delete((AVLNode<T>) root.getLeft(), data));
        } else if (diff > 0) {
            root.setRight(delete((AVLNode<T>) root.getRight(), data));
        } else {
            root = performDelete(root);
        }

        if (root == null) {
            return null;
        }

        calculateHeight(root);
        int balanceWeight = getBalanceWeight(root);

        if (Math.abs(balanceWeight) <= 1) {
            return root;
        }

        // Left-left case
        if (balanceWeight > 1 && getBalanceWeight(root.getLeft()) >= 0) {
            return rightRotate(root);
        }
        // Right-right case
        if (balanceWeight < -1 && getBalanceWeight(root.getRight()) <= 0) {
            return leftRotate(root);
        }
        //Left-Right case
        if (balanceWeight > 1 && getBalanceWeight(root.getLeft()) < 0) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        //Right-left case
        if (balanceWeight < -1 && getBalanceWeight(root.getRight()) > 0) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
        return root;
    }

    private AVLNode<T> performDelete(AVLNode<T> root) {
        if (BinaryTree.isLeaf(root)) {
            return null;
        }
        if (BinaryTree.hasOnlyOneChild(root)) {
            return (AVLNode<T>) (root.getLeft() != null ? root.getLeft() : root.getRight());
        }

        T nextGreaterData = minimum(root.getRight());
        root.setData(nextGreaterData);
        root.setRight(delete((AVLNode<T>) root.getRight(), nextGreaterData));

        return root;
    }


    private AVLNode<T> leftRotate(Node<T> root) {
        Node<T> newRoot = root.getRight();

        root.setRight(root.getRight().getLeft());
        newRoot.setLeft(root);

        calculateHeight(root);
        calculateHeight(newRoot);

        return (AVLNode<T>) newRoot;
    }

    private AVLNode<T> rightRotate(Node<T> root) {
        Node<T> newRoot = root.getLeft();

        root.setLeft(root.getLeft().getRight());
        newRoot.setRight(root);

        calculateHeight(root);
        calculateHeight(newRoot);

        return (AVLNode<T>) newRoot;
    }

    private void calculateHeight(Node<T> root) {
        ((AVLNode<T>) root).setHeight(1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight())));
    }

    private int getBalanceWeight(Node<T> root) {
        return getHeight(root.getLeft()) - getHeight(root.getRight());
    }

    private int getHeight(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return ((AVLNode<T>) node).getHeight();
    }

    private T minimum(Node<T> root) {
        Node<T> result = root;
        for (Node<T> node = root; node != null; node = node.getLeft()) {
            result = node;
        }
        return result.getData();
    }

    public Node<T> getRoot() {
        return this.root;
    }

    private static class AVLNode<T extends Comparable<T>> extends Node<T> {

        private int height = 1;

        public AVLNode(T data, Node<T> left, Node<T> right) {
            super(data, left, right);
        }

        public AVLNode(T data) {
            super(data);
        }

        public AVLNode(T data, int height) {
            super(data);
            this.setHeight(height);
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
