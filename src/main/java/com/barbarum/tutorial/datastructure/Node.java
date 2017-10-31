package com.barbarum.tutorial.datastructure;

class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
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

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getPrevious() {
        return this.getLeft();
    }

    public void setPrevious(Node<T> previous) {
        this.setLeft(previous);
    }

    public Node<T> getNext() {
        return this.getRight();
    }

    public void setNext(Node<T> next) {
        this.setRight(next);
    }

    public static <T> Node<T> createLeftNode(T data, Node<T> left) {
        return new Node<>(data, left, null);
    }

    public static <T> Node<T> createRightNode(T data, Node<T> right) {
        return new Node<>(data, null, right);
    }
}
