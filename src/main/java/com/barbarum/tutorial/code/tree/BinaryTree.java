package com.barbarum.tutorial.code.tree;


import com.barbarum.tutorial.code.tree.data.Node;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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
    public static <T> int size(Node<T> root) {
        return root == null ? 0 : 1 + size(root.getLeft()) + size(root.getRight());
    }

    /**
     * Binary Tree - Count max depth of current tree.
     *
     * @param root root node.
     * @param <T>  generalized type.
     * @return max depth of current tree.
     */
    public static <T> int maxDepth(Node<T> root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight()));
    }

    /**
     * Binary Tree - Count minimum depth of current tree.
     *
     * @param root root node.
     * @param <T>  generalized type.
     * @return max depth of current tree.
     */
    public static <T> int minDepth(Node<T> root) {
        return root == null ? 0 : 1 + Math.min(minDepth(root.getLeft()), minDepth(root.getRight()));
    }

    /**
     * In-order traversal
     *
     * @see #traversalLDR(Node, LinkedList, BiConsumer)
     */
    public static <T> List<T> traversalLDR(Node<T> root) {
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
    public static <T> LinkedList<Node<T>> traversalLDR(Node<T> root, LinkedList<Node<T>> result, BiConsumer<Node<T>, Node<T>> consumer) {
        if (root == null) {
            return result;
        }

        // Traversal LDR
        if (root.getLeft() != null) {
            traversalLDR(root.getLeft(), result, consumer);
        }

        if (consumer != null) {
            consumer.accept(result.peekLast(), root);
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
    public static <T> List<T> traversalLRD(Node<T> root, List<T> result) {
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

    public static <T> List<T> breadthTraversalFirst(Node<T> root) {
        if (root == null) {
            return Collections.emptyList();
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        List<T> result = new ArrayList<>();
        breadthTraversalFirst(queue, result);

        return result;
    }

    private static <T> void breadthTraversalFirst(Queue<Node<T>> queue, List<T> result) {
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            result.add(node.getData());

            addNodeIfNotNull(queue, node.getLeft());
            addNodeIfNotNull(queue, node.getRight());
        }
    }

    public static <T> boolean isLeaf(Node<T> root) {
        return root != null && root.getLeft() == null && root.getRight() == null;
    }

    public static <T> boolean hasOnlyOneChild(Node<T> root) {
        if (root == null) {
            return false;
        }
        return (root.getLeft() != null && root.getRight() == null)
                || (root.getLeft() == null && root.getRight() != null);
    }

    public static <T> boolean hasTwoChildren(Node<T> root) {
        return root != null && root.getLeft() != null && root.getRight() != null;
    }

    public static <T> boolean isCousin(Node<T> root, Node<T> a, Node<T> b) {
        return a != b
                && getDepth(root, a) == getDepth(root, b)
                && !isSibling(root, a, b);
    }

    public static <T> int getDepth(Node<T> root, Node<T> node) {
        return getDepth(root, node, 1);
    }

    private static <T> int getDepth(Node<T> root, Node<T> node, int currentDepth) {
        if (root == null) {
            return -1;
        }
        if (root == node) {
            return currentDepth;
        }
        int depth = getDepth(root.getLeft(), node, currentDepth + 1);
        if (depth == -1) {
            depth = getDepth(root.getRight(), node, currentDepth + 1);
        }
        return depth;
    }

    public static <T> boolean isSibling(Node<T> root, Node<T> a, Node<T> b) {
        if (root == null) {
            return false;
        }
        return (root.getLeft() == a && root.getRight() == b)
                || (root.getLeft() == b && root.getRight() == a)
                || isSibling(root.getLeft(), a, b)
                || isSibling(root.getRight(), a, b);
    }

    public static <T> boolean isComplete(Node<T> root) {
        if (root == null) {
            return true;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        boolean previousNotComplete = false;

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            addNodeIfNotNull(queue, node.getLeft());
            addNodeIfNotNull(queue, node.getRight());

            // if a node has right branch but doesn't have left branch, it's not a complete tree.
            if (node.getLeft() == null && node.getRight() != null) {
                return false;
            }
            if (previousNotComplete && !isLeaf(node)) {
                return false;
            }
            if (hasOnlyOneChild(root) && !previousNotComplete) {
                previousNotComplete = true;
            }
        }
        return true;
    }

    private static <T> void addNodeIfNotNull(Queue<Node<T>> queue, Node<T> node) {
        if (node != null) {
            queue.add(node);
        }
    }

    public static <T extends Comparable<T>> Node<T> lookup(Node<T> root, T target) {
        if (root == null) {
            return null;
        }
        int result = target.compareTo(root.getData());
        return result == 0 ? root : lookup(result < 0 ? root.getLeft() : root.getRight(), target);
    }

    /**
     * Binary Tree - same tree
     *
     * @param a   the given tree A's root node.
     * @param b   the given tree B's root node.
     * @param <T> generalized type.
     * @return true if each node's value is the same, otherwise false.
     */
    public static <T extends Comparable<T>> boolean identical(Node<T> a, Node<T> b) {
        if (a == null || b == null) {
            return a == b;
        }

        return a.getData().equals(b.getData())
                && identical(a.getLeft(), b.getLeft())
                && identical(a.getRight(), b.getRight());
    }

    /**
     * Check if binary tree b is a sub-tree of tree a.
     *
     * @param a the given binary tree a.
     * @param b the given binary tree b.
     * @return true if b is a subtree of a, otherwise false.
     */
    public static <T extends Comparable<T>> boolean isSubTree(Node<T> a, Node<T> b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        Node<T> subTree = lookup(a, b.getData());
        return subTree != null && identical(subTree, b);

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
    public static <T> void collectPaths(Node<T> root, List<List<T>> result) {
        if (root == null) {
            return;
        }
        collectPaths(root, result, new ArrayList<>());
    }

    private static <T> void collectPaths(Node<T> root, List<List<T>> result, List<T> steps) {
        // Abandon empty branch of single child case.
        if (root == null) {
            return;
        }
        steps.add(root.getData());

        // Handle leaf case
        if (isLeaf(root)) {
            result.add(new ArrayList<>(steps));
        } else {
            collectPaths(root.getLeft(), result, steps);
            collectPaths(root.getRight(), result, steps);
        }

        steps.remove(steps.size() - 1);
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

        Node<T> left = root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(left);

        mirror(root.getLeft());
        mirror(root.getRight());
    }

    /**
     * Count how many tree variants can be built for the given number of nodes.
     *
     * @param total the given number of nodes.
     * @return number of tree variants.
     */
    public static int countTrees(int total) {
        if (total < 0) {
            throw new IllegalArgumentException();
        }
        int[] cache = new int[total + 1];
        cache[0] = 1;
        cache[1] = 1;

        for (int length = 2; length <= total; length++) {
            for (int nodesAtLeft = 0; nodesAtLeft < length; nodesAtLeft++) {
                cache[length] += cache[nodesAtLeft] * cache[length - (nodesAtLeft + 1)];
            }
        }

        return cache[total];
    }

    /**
     * Binary Tree - double tree
     *
     * @param root the given root node.
     * @param <T>  generalized type.
     */
    public static <T> void doubleTree(Node<T> root) {
        if (root == null) {
            return;
        }

        doubleTree(root.getLeft());
        doubleTree(root.getRight());
        root.setLeft(Node.createLeftNode(root.getData(), root.getLeft()));
    }

    public static void main(String args[]) {
        BinaryTreeUtil.printTree(BinarySearchTree.build(new Integer[]{0, 1, 2, 3, 4, 5}));
        System.out.println(countTrees(4));

        System.out.println(BinaryTree.breadthTraversalFirst(BinarySearchTree.build(new Integer[]{0, 1, 2, 3, 4, 5})));
    }
}
