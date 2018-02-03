package com.barbarum.tutorial.code.list;

import com.barbarum.tutorial.code.tree.data.Node;

public class LinkedList {

    public static Node<Integer> merge(Node<Integer> list1, Node<Integer> list2) {
        Node<Integer> head = new Node<>(null);
        Node<Integer> tail = head;
        Node<Integer> l1Head = list1, l2Head = list2;

        while (l1Head != null && l2Head != null) {
            if (l1Head.getData() <= l2Head.getData()) {
                tail = addToTail(tail, l1Head);
                l1Head = l1Head.getRight();
            } else {
                tail = addToTail(tail, l2Head);
                l2Head = l2Head.getRight();
            }
        }

        if (l1Head != null) {
            addToTail(tail, l1Head);
        }
        if (l2Head != null) {
            addToTail(tail, l2Head);
        }

        return head.getRight();
    }

    private static Node<Integer> addToTail(Node<Integer> tail, Node<Integer> node) {
        tail.setRight(node);
        return node;
    }

    public static Node<Integer> intersection(Node<Integer> list1, Node<Integer> list2) {
        int m = count(list1);
        int n = count(list2);

        Node<Integer> l1Head;
        Node<Integer> l2Head;

        if (m < n) {
            l1Head = list1;
            l2Head = loop(list2, n - m);
        } else {
            l1Head = loop(list1, m - n);
            l2Head = list2;
        }

        for (; l1Head != null && l2Head != null; l1Head = l1Head.getRight(), l2Head = l2Head.getRight()) {
            if (l1Head.getData().equals(l2Head.getData())) {
                return l1Head;
            }
        }

        return null;
    }

    private static Node<Integer> loop(Node<Integer> head, int skip) {
        Node<Integer> result = head;
        for (int i = 0; i < skip; i++) {
            result = result.getRight();
        }
        return result.getRight();
    }

    private static int count(Node<Integer> head) {
        int result = 0;
        for (Node<Integer> node = head; node != null; node = node.getRight()) {
            result++;
        }
        return result;
    }

    public static Node<Integer> detect(Node<Integer> list) {
        Node<Integer> slow = list;
        Node<Integer> fast = list;

        do {
            slow = move(slow, 1);
            fast = move(fast, 2);
        } while (slow != null && fast != null && slow != fast);

        if (slow == null || fast == null) {
            return null;
        }

        for (slow = list; slow != null && fast != null && slow != fast; ) {
            slow = slow.getRight();
            fast = fast.getRight();
        }

        return slow;
    }

    private static Node<Integer> move(Node<Integer> node, int step) {
        Node<Integer> result = node;
        for (; result != null && step > 0; step--) {
            result = result.getRight();
        }
        return result;
    }

}
