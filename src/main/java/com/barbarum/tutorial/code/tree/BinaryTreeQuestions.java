package com.barbarum.tutorial.code.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BinaryTreeQuestions {

    public static boolean isCousin(Node root, Node a, Node b) {
        return a != b
                && (getLevel(root, a) == getLevel(root, b))
                && !isSibling(root, a, b);
    }

    private static int getLevel(Node root, Node node) {
        return getLevel(root, node, 1);
    }

    private static int getLevel(Node root, Node node, int currentDepth) {
        if (root == null) {
            return -1;
        }
        if (root == node) {
            return currentDepth;
        }
        int depth = getLevel(root.getLeft(), node, currentDepth + 1);
        if (depth == -1) {
            depth = getLevel(root.getRight(), node, currentDepth + 1);
        }
        return depth;
    }

    private static boolean isSibling(Node root, Node a, Node b) {
        if (root == null) {
            return false;
        }
        return (root.getLeft() == a && root.getRight() == b)
                || (root.getLeft() == b && root.getRight() == a)
                || isSibling(root.getLeft(), a, b)
                || isSibling(root.getRight(), a, b);
    }

    public static void removeNodesOnPathSumLessThanK(Node<Integer> root, int sum) {
        sumAndRemove(root, sum);
    }

    private static int sumAndRemove(Node<Integer> root, int sum) {
        if (root == null || sum <= 0) {
            return sum;
        }

        int leftRemaining = sumAndRemove(root.getLeft(), sum - root.getData());
        if (leftRemaining > 0) {
            root.setLeft((Node<Integer>) null);
        }
        int rightRemaining = sumAndRemove(root.getRight(), sum - root.getData());
        if (rightRemaining > 0) {
            root.setRight((Node<Integer>) null);
        }

        return Math.min(leftRemaining, rightRemaining);
    }

    public static List<Node<Integer>> printBottomView(Node<Integer> root) {
        Map<Integer, WrappedNode<Integer>> cache = new HashMap<>();
        traversal(root, 0, 1, cache);
        return convert(cache);
    }

    private static List<Node<Integer>> convert(Map<Integer, WrappedNode<Integer>> cache) {
        List<Node<Integer>> result = new ArrayList<>();
        for (WrappedNode<Integer> node : cache.values()) {
            result.add(node.node);
        }
        return result;
    }

    private static void traversal(Node<Integer> root, int currentHorizontalDistance, int depth, Map<Integer, WrappedNode<Integer>> cache) {
        if (root == null) {
            return;
        }
        traversal(root.getLeft(), currentHorizontalDistance - 1, depth + 1, cache);

        if (!cache.containsKey(currentHorizontalDistance)) {
            cache.put(currentHorizontalDistance, new WrappedNode<>(root, depth));
        } else {
            WrappedNode<Integer> wrappedNode = cache.get(currentHorizontalDistance);
            if (wrappedNode.depth <= depth) {
                wrappedNode.node = root;
                wrappedNode.depth = depth;
            }
        }
        traversal(root.getRight(), currentHorizontalDistance + 1, depth + 1, cache);
    }

    private static class WrappedNode<T> {
        Node<T> node;
        int depth;

        public WrappedNode() {

        }

        public WrappedNode(Node<T> node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public static boolean symmetricTree(Node<Integer> root) {
        if (root == null) {
            return true;
        }
        Node<Integer> mirrorRight = mirror(root.getRight());
        return checkSameTree(root.getLeft(), mirrorRight);
    }

    private static Node<Integer> mirror(Node<Integer> root) {
        if (root == null) {
            return null;
        }
        Node<Integer> node = new Node<Integer>(root.getData());
        node.setChildren(mirror(root.getRight()), mirror(root.getLeft()));
        return node;
    }

    private static boolean checkSameTree(Node<Integer> tree1, Node<Integer> tree2) {
        if (tree1 == null || tree2 == null) {
            return tree1 == tree2;
        }
        return tree1.getData().equals(tree2.getData())
                && checkSameTree(tree1.getLeft(), tree2.getLeft())
                && checkSameTree(tree1.getRight(), tree2.getRight());
    }

    public static Node<Integer> convertToDoubleLinkedList(Node<Integer> root) {
        Node<Integer> head = new Node<Integer>(null);
        traversalLDR(root, head);

        if (head.getRight() != null) {
            head.getRight().setLeft((Node<Integer>) null);
        }

        return head.getRight();
    }

    private static Node<Integer> traversalLDR(Node<Integer> root, Node<Integer> tail) {
        if (root == null) {
            return tail;
        }
        tail = traversalLDR(root.getLeft(), tail);

        tail.setRight(root);
        root.setLeft(tail);
        tail = root;

        tail = traversalLDR(root.getRight(), tail);

        return tail;
    }

    public static void main(String args[]) {
        Node<Integer> root1 = new Node<Integer>(1);
        root1.setChildren(2, 3);
        root1.getLeft().setChildren(4, 5);
        root1.getRight().setChildren(6, 7);

        System.out.println(printBottomView(root1)
                .stream()
                .map(Node::getData)
                .collect(Collectors.toList()));
    }
}
