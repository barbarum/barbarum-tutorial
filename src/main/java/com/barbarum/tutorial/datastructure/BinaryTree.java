package com.barbarum.tutorial.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://en.wikipedia.org/wiki/Binary_tree
 * <p>
 * Definition:
 * In computer science, a binary tree is a tree data structure in which each node has at most two children, which are referred to as the left child and the right child.
 * A recursive definition using just set theory notions is that a (non-empty) binary tree is a triple (L, S, R),where L and R are binary trees or the empty set and S is a singleton set.[1]
 */
public class BinaryTree {

    /**
     * Binary Tree - Counts the size of binary lookup tree.
     *
     * @param root root node
     * @param <T>  generalized type.
     * @return size of the current binary lookup tree.
     */
    public static <T extends Comparable<T>> int size(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.getLeft()) + size(root.getRight());
    }

    /**
     * Binary Tree - Count max depth of current tree.
     *
     * @param root root node.
     * @param <T>  generalized type.
     * @return max depth of current tree.
     */
    public static <T extends Comparable<T>> int maxDepth(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight()));
    }

    /**
     * Binary Tree - Print Tree in nature order
     */
    public static <T extends Comparable<T>> void printTree(Node<T> root) {
        List<T> result = traversalLDR(root, new ArrayList<>());

        StringBuilder builder = new StringBuilder();
        result.forEach((item) -> builder.append(item).append(" "));
        builder.trimToSize();
        System.out.println(builder.toString());
    }

    /**
     * @see #traversalLDR(Node, LinkedList, Consumer)
     */
    public static <T extends Comparable<T>> List<T> traversalLDR(Node<T> root, List<T> result) {
        Queue<Node<T>> nodeResult = traversalLDR(root, new LinkedList<>(), null);
        return nodeResult.stream()
                .map(Node::getData)
                .collect(Collectors.toList());
    }

    /**
     * Binary Tree - In-order traversal.
     * <p>
     * Order => left, root, right
     * </p>
     *
     * @param root   root node
     * @param result the given empty list
     * @param <T>    generalized type.
     * @return result
     */
    public static <T extends Comparable<T>> LinkedList<Node<T>> traversalLDR(Node<T> root, LinkedList<Node<T>> result, Consumer<Node<T>> consumer) {
        if (root == null) {
            return result;
        }

        if (consumer != null && root.getLeft() != null) {
            consumer.accept(root);
        }

        // Traversal LDR
        if (root.getLeft() != null) {
            traversalLDR(root.getLeft(), result, consumer);
        }
        result.add(root);
        if (root.getRight() != null) {
            traversalLDR(root.getRight(), result, consumer);
        }

        return result;
    }

    /**
     * Binary Tree - Post-Order/Bottom-Up traversal.
     * <p>
     * Order => left, right, root
     * </p>
     *
     * @param root   root node
     * @param result the given empty list
     * @param <T>    generalized type.
     * @return result
     */
    public static <T extends Comparable<T>> List<T> traversalLRD(Node<T> root, List<T> result) {
        if (root == null) {
            return result;
        }
        if (root.getLeft() != null) {
            traversalLRD(root.getLeft(), result);
        }
        if (root.getRight() != null) {
            traversalLRD(root.getRight(), result);
        }
        result.add(root.getData());
        return result;
    }

    /**
     * Binary Tree - has path sum
     *
     * @return
     */
    public static boolean hasPathSum(Node<Long> root, long target) {
        if (root == null) {
            return target == 0;
        }
        return hasPathSum(root.getLeft(), target - root.getData())
                || hasPathSum(root.getRight(), target - root.getData());
    }


    /**
     * Binary Tree - collect paths
     *
     * @param root   the given tree.
     * @param result the result list.
     * @param <T>    generalized type.
     */
    public static <T extends Comparable<T>> void collectPaths(Node<T> root, List<List<T>> result) {
        if (root == null) {
            return;
        }
        collectPaths(root, result, new ArrayList<>());
    }

    private static <T extends Comparable<T>> void collectPaths(Node<T> root, List<List<T>> result, List<T> steps) {
        steps.add(root.getData());
        // Handle leaf case
        if (root.getLeft() == null && root.getRight() == null) {
            result.add(steps);
            return;
        }
        // Double children case
        if (root.getLeft() != null && root.getRight() != null) {
            collectPaths(root.getLeft(), result, new ArrayList<>(steps));
            collectPaths(root.getRight(), result, new ArrayList<>(steps));
        }
        // Single child case
        collectPaths(root.getLeft() == null ? root.getLeft() : root.getRight(), result, steps);
    }

    /**
     * Binary Tree - mirror
     *
     * @param root the given root node.
     * @param <T>  generalized type.
     */
    public static <T extends Comparable<T>> void mirror(Node<T> root) {
        if (root == null) {
            return;
        }

        mirror(root.getLeft());
        mirror(root.getRight());

        Node<T> left = root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(left);
    }


    public static void heapify(int[] elements) {

    }


    public static void main(String args[]) {
        printTree(BinarySearchTree.buildBST(new Integer[]{0, 1, 2, 3, 4, 5}));
    }

    /**
     * Count how many tree variants can be built for the given number of nodes.
     *
     * @param nodes the given number of nodes.
     * @return number of tree variants.
     */
    public static int countTrees(int nodes) {
        if (nodes == 0 || nodes == 1) {
            return 1;
        }

        return IntStream.range(0, nodes)
                .map(valve -> countTrees(valve) * countTrees((nodes - 1) - valve))
                .sum();

    }

    /**
     * Binary Tree - double tree
     *
     * @param root the given root node.
     * @param <T>  generalized type.
     */
    public static <T extends Comparable<T>> void doubleTree(Node<T> root) {
        if (root == null) {
            return;
        }

        doubleTree(root.getLeft());
        doubleTree(root.getRight());
        root.setLeft(Node.createLeftNode(root.getData(), root.getLeft()));
    }

    /**
     * Binary Tree - same tree
     *
     * @param rootA the given tree A's root node.
     * @param rootB the given tree B's root node.
     * @param <T>   generalized type.
     * @return true if each node's value is the same, otherwise false.
     */
    public static <T extends Comparable<T>> boolean sameTree(Node<T> rootA, Node<T> rootB) {
        if (rootA == null || rootB == null) {
            return rootA == rootB;
        }

        return rootA.getData().equals(rootB.getData())
                && sameTree(rootA.getLeft(), rootB.getLeft())
                && sameTree(rootA.getRight(), rootB.getRight());
    }
}
