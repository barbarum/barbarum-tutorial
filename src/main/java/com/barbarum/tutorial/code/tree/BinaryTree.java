package com.barbarum.tutorial.code.tree;


import com.barbarum.tutorial.code.tree.data.Node;

import java.util.*;
import java.util.function.BiConsumer;
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
     * Binary Tree - Count minimum depth of current tree.
     *
     * @param root root node.
     * @param <T>  generalized type.
     * @return max depth of current tree.
     */
    public static <T extends Comparable<T>> int minDepth(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.min(minDepth(root.getLeft()), minDepth(root.getRight()));
    }

    /**
     * Binary Tree - Print Tree in nature order
     */
    public static <T extends Comparable<T>> void printTree(Node<T> root) {
        List<T> result = traversalLDR(root);

        StringBuilder builder = new StringBuilder();
        result.forEach((item) -> builder.append(item).append(" "));
        builder.trimToSize();
        System.out.println(builder.toString());
    }

    /**
     * @see #traversalLDR(Node, LinkedList, BiConsumer)
     */
    public static <T extends Comparable<T>> List<T> traversalLDR(Node<T> root) {
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
    public static <T extends Comparable<T>> LinkedList<Node<T>> traversalLDR(Node<T> root, LinkedList<Node<T>> result, BiConsumer<Node<T>, Node<T>> consumer) {
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

    private static <T extends Comparable<T>> boolean isLeaf(Node<T> root) {
        return root != null && root.getLeft() == null && root.getRight() == null;
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

    /**
     * Definition of height balanced tree - the height differences of any node's left and right subtree is not greater than 1.
     *
     * @param root the given tree.
     * @param <T>  generalized type.
     * @return true if the tree is balanced, otherwise false
     */
    public static <T> boolean examineIfHeightBalanced(Node<T> root) {
        return examineIfHeightBalanced(root, new HashMap<>());

    }

    private static <T> boolean examineIfHeightBalanced(Node<T> root, Map<Node<T>, Integer> mapping) {
        if (root == null) {
            return true;
        }

        int leftHeight = getHeight(root.getLeft(), mapping);
        int rightHeight = getHeight(root.getRight(), mapping);

        return Math.abs(leftHeight - rightHeight) <= 1
                && examineIfHeightBalanced(root.getLeft(), mapping)
                && examineIfHeightBalanced(root.getRight(), mapping);
    }

    private static <T> int getHeight(Node<T> node, Map<Node<T>, Integer> mapping) {
        if (node == null) {
            return 0;
        }
        if (mapping.containsKey(node)) {
            return mapping.get(node);
        }
        int height = 1 + Math.max(getHeight(node.getLeft(), mapping), getHeight(node.getRight(), mapping));
        mapping.put(node, height);

        return height;
    }
}
