package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.*;

public class BinaryTreeView {

    public static List<Integer> left(Node<Integer> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        left(root, 0, result);
        return result;
    }

    private static void left(Node<Integer> root, int depth, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (depth == result.size()) {
            result.add(root.getData());
        }
        left(root.getLeft(), depth + 1, result);
        left(root.getRight(), depth + 1, result);
    }

    public static List<Integer> right(Node<Integer> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        right(root, 0, result);

        return result;
    }

    private static void right(Node<Integer> root, int depth, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (depth == result.size()) {
            result.add(root.getData());
        }
        right(root.getRight(), depth + 1, result);
        right(root.getLeft(), depth + 1, result);
    }

    /**
     * Using level-order traversal.
     */
    public static int[] bottom(Node<Integer> root) {
        if (root == null) {
            return null;
        }
        Map<Node<Integer>, Integer> cache = new HashMap<>();
        calculateHorizontalDistance(root, 0, cache);
        int minimum = findMinimumHorizontalDistance(cache);
        int maximum = findMaximumHorizontalDistance(cache);

        int[] result = new int[maximum - minimum + 1];
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);

        levelOrderTraversalForBottom(queue, result, minimum, cache);
        return result;
    }

    private static void levelOrderTraversalForBottom(Queue<Node<Integer>> queue, int[] result, int base, Map<Node<Integer>, Integer> cache) {
        while (!queue.isEmpty()) {
            Node<Integer> node = queue.poll();
            addChildIfNotNull(node.getLeft(), queue);
            addChildIfNotNull(node.getRight(), queue);

            int horizontalDistance = cache.get(node);
            result[horizontalDistance - base] = node.getData();
        }
    }

    public static int[] top(Node<Integer> root) {
        if (root == null) {
            return null;
        }
        Map<Node<Integer>, Integer> cache = new HashMap<>();
        calculateHorizontalDistance(root, 0, cache);

        int minimum = findMinimumHorizontalDistance(cache);
        int maximum = findMaximumHorizontalDistance(cache);

        int[] result = new int[maximum - minimum + 1];
        initializeResult(result);

        levelOrderTraversalForTop(root, cache, result, minimum);
        return result;
    }

    private static void levelOrderTraversalForTop(Node<Integer> root, Map<Node<Integer>, Integer> cache, int[] result, int base) {
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<Integer> node = queue.poll();
            addChildIfNotNull(node.getLeft(), queue);
            addChildIfNotNull(node.getRight(), queue);

            int index = cache.get(node) - base;
            if (result[index] == Integer.MIN_VALUE) {
                result[index] = node.getData();
            }
        }
    }

    public static List<List<Integer>> vertical(Node<Integer> root) {
        if (root == null) {
            return Collections.emptyList();
        }

        Map<Node<Integer>, Integer> cache = new HashMap<>();
        calculateHorizontalDistance(root, 0, cache);

        int minimum = findMinimumHorizontalDistance(cache);
        int maximum = findMaximumHorizontalDistance(cache);

        List<List<Integer>> result = new ArrayList<>();
        initializeResult(result, minimum, maximum);

        levelOrderTraversal(root, minimum, result, cache);

        return result;
    }

    private static void levelOrderTraversal(Node<Integer> root, int base, List<List<Integer>> result, Map<Node<Integer>, Integer> cache) {
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<Integer> node = queue.poll();
            addChildIfNotNull(node.getLeft(), queue);
            addChildIfNotNull(node.getRight(), queue);

            int index = cache.get(node) - base;
            result.get(index).add(node.getData());
        }
    }

    private static void calculateHorizontalDistance(Node<Integer> root, int horizontalDistance, Map<Node<Integer>, Integer> cache) {
        if (root == null) {
            return;
        }
        cache.put(root, horizontalDistance);
        calculateHorizontalDistance(root.getLeft(), horizontalDistance - 1, cache);
        calculateHorizontalDistance(root.getRight(), horizontalDistance + 1, cache);
    }

    private static int findMinimumHorizontalDistance(Map<Node<Integer>, Integer> cache) {
        int result = Integer.MAX_VALUE;
        for (int item : cache.values()) {
            result = Math.min(result, item);
        }
        return result;
    }

    private static int findMaximumHorizontalDistance(Map<Node<Integer>, Integer> cache) {
        int result = Integer.MIN_VALUE;
        for (int item : cache.values()) {
            result = Math.max(result, item);
        }
        return result;
    }

    private static void initializeResult(int[] result) {
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.MIN_VALUE;
        }
    }

    private static void addChildIfNotNull(Node<Integer> node, Queue<Node<Integer>> queue) {
        if (node == null) {
            return;
        }
        queue.add(node);
    }

    private static void initializeResult(List<List<Integer>> result, int minimum, int maximum) {
        for (int i = minimum; i <= maximum; i++) {
            result.add(new ArrayList<>());
        }
    }

}
