package com.barbarum.tutorial.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 *
 */
public class BinaryTree {

    /**
     * Binary Tree - Counts the size of binary search tree.
     *
     * @param root root node
     * @param <T>  generalized type.
     * @return size of the current binary search tree.
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
     * @see #traversalLDR(Node, List, BiConsumer)
     */
    public static <T extends Comparable<T>> List<T> traversalLDR(Node<T> root, List<T> result) {
        List<Node<T>> nodeResult = traversalLDR(root, new ArrayList<>(), null);
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
    public static <T extends Comparable<T>> List<Node<T>> traversalLDR(Node<T> root, List<Node<T>> result, BiConsumer<Node<T>, Node<T>> consumer) {
        if (root == null) {
            return result;
        }

        Optional.ofNullable(root.getLeft())
                .ifPresent((item) -> traversalLDR(item, result, consumer));

        Optional.ofNullable(consumer)
                .ifPresent((action) -> action.accept(result.isEmpty() ? null : result.get(result.size() - 1), root));

        result.add(root);

        Optional.ofNullable(root.getRight())
                .ifPresent((item) -> traversalLDR(item, result, consumer));
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


    /**
     * Binary Search Tree -> Lookup
     *
     * @param root   root node
     * @param target the searched data
     * @param <T>    Generalized type
     * @return true if found, otherwise false.
     */
    public static <T extends Comparable<T>> boolean lookup(Node<T> root, T target) {
        if (root == null) {
            return false;
        }
        int result = target.compareTo(root.getData());
        return result == 0 || lookup(result < 0 ? root.getLeft() : root.getRight(), target);
    }

    /**
     * Binary Search Tree -> Insert (Unbalanced)
     *
     * @param root   root node
     * @param target the inserted data
     * @param <T>    generalized type.
     */
    public static <T extends Comparable<T>> Node<T> insert(Node<T> root, T target) {
        if (root == null) {
            return new Node<T>(target);
        }
        int result = target.compareTo(root.getData());
        if (result <= 0) {
            root.setLeft(insert(root.getLeft(), target));
        } else {
            root.setRight(insert(root.getRight(), target));
        }

        return root;
    }

    /**
     * Binary Search Tree -> Build (Unbalanced)
     * <p>
     * The method would try making the tree as balanced as possible.
     * </p>
     *
     * @param data data array
     * @param <T>  Generalized type.
     * @return the new built binary search tree.
     */
    public static <T extends Comparable<T>> Node<T> buildBST(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return buildBST(data, 0, data.length - 1);
    }

    private static <T extends Comparable<T>> Node<T> buildBST(T[] data, int start, int end) {
        if (start > end || start >= data.length || end < 0) {
            return null;
        }
        int middle = (start + end) / 2;
        return new Node<T>(data[middle], buildBST(data, start, middle - 1), buildBST(data, middle + 1, end));
    }


    /**
     * Binary Search Tree - Find the minimum data from the given tree.
     *
     * @param root root node.
     * @param <T>  generalized type.
     * @return minimum data, or null if the tree is empty.
     */
    public static <T extends Comparable<T>> T minValue(Node<T> root) {
        if (root == null) {
            return null;
        }
        return Optional.ofNullable(minValue(root.getLeft()))
                .filter(item -> item.compareTo(root.getData()) <= 0)
                .orElse(root.getData());
    }


    /**
     * Binary Search Tree - double tree
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

        Node<T> node = Node.createLeftNode(root.getData(), root.getLeft());
        root.setLeft(node);
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

    public static int countTrees(int nodes) {
        if (nodes == 0 || nodes == 1) {
            return 1;
        }

        int numOfTrees = 0;
        for (int i = 0; i < nodes; i++) {
            numOfTrees += countTrees(i) * countTrees(nodes - i - 1);
        }

        return numOfTrees;
    }

    /**
     * Binary Search Tree - Examine if the given tree is a binary search tree.
     *
     * @param root the given root node.
     * @param <T>  generalized type.
     * @return true if it's, otherwise false.
     */
    public static <T extends Comparable<T>> boolean examine(Node<T> root) {
        return root == null || examine(root, null, null);
    }

    private static <T extends Comparable<T>> boolean examine(Node<T> root, T minData, T maxData) {
        // examine left child is less or equals to root node's data if exists.
        if (minData != null && minData.compareTo(root.getData()) > 0) {
            return false;
        }

        // examine right child is greater than root node's data if exists.
        if (maxData != null && maxData.compareTo(root.getData()) <= 0) {
            return false;
        }

        return examine(root.getLeft(), minData, root.getData())
                && examine(root.getRight(), root.getData(), maxData);
    }

    /**
     * Binary Search Tree - Convert a binary search tree to double linked list
     *
     * @param root the given root node.
     * @param <T>  generalized type.
     * @return head node
     */
    public static <T extends Comparable<T>> Node<T> convert(Node<T> root) {
        List<Node<T>> result = traversalLDR(root, new ArrayList<>(), (previous, next) -> {
            if (previous != null) {
                previous.setNext(next);
                next.setPrevious(previous);
            }
        });
        if (result.isEmpty()) {
            return null;
        }
        result.get(result.size() - 1).setNext(result.get(0));
        return result.get(0);
    }

    public static void heapify(int[] elements) {

    }


    public static void main(String args[]) {
        printTree(buildBST(new Integer[]{0, 1, 2, 3, 4, 5}));
    }
}
