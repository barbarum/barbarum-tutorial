package com.barbarum.tutorial.code.tree.data;

public class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;

    private Node<T> parent;

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.setLeft(left);
        this.setRight(right);
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> setLeft(T left) {
        return setLeft(new Node<T>(left));
    }

    public Node<T> setLeft(Node<T> left) {
        this.left = left;
        this.left.setParent(this);
        return this.left;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> setRight(T right) {
        return setRight(new Node<T>(right));
    }

    public Node<T> setRight(Node<T> right) {
        this.right = right;
        this.right.setParent(this);
        return this.right;
    }

    public void setChildren(Node<T> left, Node<T> right) {
        this.setLeft(left);
        this.setRight(right);
    }

    public void setChildren(T left, T right) {
        setChildren(new Node<T>(left), new Node<T>(right));
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> setParent(Node<T> parent) {
        this.parent = parent;
        return this.parent;
    }

    public static <T> Node<T> createLeftNode(T data, Node<T> left) {
        return new Node<>(data, left, null);
    }

    public static <T> Node<T> createRightNode(T data, Node<T> right) {
        return new Node<>(data, null, right);
    }
}
