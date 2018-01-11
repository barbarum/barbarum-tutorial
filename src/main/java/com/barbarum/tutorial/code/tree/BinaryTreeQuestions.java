package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.*;

public class BinaryTreeQuestions {

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

    public static List<Integer> collectSingleChildNodes(Node<Integer> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        collectSingleChildNodes(root, result);
        return result;
    }

    private static void collectSingleChildNodes(Node<Integer> root, List<Integer> result) {
        if (root == null || BinaryTree.isLeaf(root)) {
            return;
        }
        if (BinaryTree.hasOnlyOneChild(root)) {
            result.add(root.getLeft() != null ? root.getLeft().getData() : root.getRight().getData());
        }
        collectSingleChildNodes(root.getLeft(), result);
        collectSingleChildNodes(root.getRight(), result);
    }

    public static boolean symmetric(Node<Integer> root) {
        if (root == null) {
            return true;
        }
        Node<Integer> mirrorRight = mirror(root.getRight());
        return BinaryTree.identical(root.getLeft(), mirrorRight);
    }

    private static Node<Integer> mirror(Node<Integer> root) {
        return root == null ?
                null : new Node<>(root.getData(), mirror(root.getRight()), mirror(root.getLeft()));
    }

    public static Node<Integer> buildBST(int[] inOrder, int[] preOrder) {

        if (inOrder == null || preOrder == null || inOrder.length == 0 || preOrder.length == 0) {
            return null;
        }
        if (inOrder.length != preOrder.length) {
            throw new IllegalArgumentException();
        }
        return buildBST(inOrder, preOrder, 0, inOrder.length - 1, 0, preOrder.length - 1);
    }

    private static Node<Integer> buildBST(int[] inOrder, int[] preOrder, int inOrderStart, int inOrderEnd, int preOrderStart, int preOrderEnd) {// [1,2,3], [1,3,2], 0, 2, 0, 2
        if (inOrderStart > inOrderEnd) {
            return null;
        }
        int inOrderRootIndex = findRootIndex(inOrder, inOrderStart, inOrderEnd, preOrder[preOrderEnd]);// [1,2,3], 0, 2, 2 -> 1
        int leftDistance = inOrderRootIndex - inOrderStart; // 1

        Node<Integer> left = buildBST(inOrder, preOrder, inOrderStart, inOrderRootIndex - 1, preOrderStart, preOrderStart + leftDistance - 1); // 0, 0, 0, 0
        Node<Integer> right = buildBST(inOrder, preOrder, inOrderRootIndex + 1, inOrderEnd, preOrderStart + leftDistance, preOrderEnd - 1);// 2,2,1,1

        Node<Integer> root = new Node<Integer>(preOrder[preOrderEnd]);// 2

        root.setLeft(left);
        root.setRight(right);

        return root;
    }

    private static int findRootIndex(int[] items, int start, int end, int target) {
        for (int i = start; i <= end; i++) {
            if (items[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static Node<Integer> buildBST(int[] parents) {
        if (parents == null || parents.length == 0) {
            return null;
        }

        Node<Integer> nodes[] = new Node[parents.length];
        int rootIndex = buildBST(parents, nodes);

        return nodes[rootIndex];
    }

    private static int buildBST(int[] parents, Node<Integer>[] nodes) {
        int rootIndex = -1;

        for (int i = 0; i < parents.length; i++) {
            Node<Integer> currentNode = getOrDefault(nodes, i);
            if (parents[i] != -1) {
                Node<Integer> parentNode = getOrDefault(nodes, parents[i]);
                setChild(currentNode, parentNode);
            } else {
                rootIndex = i;
            }
        }

        return rootIndex;
    }

    private static void setChild(Node<Integer> node, Node<Integer> parent) {
        if (parent.getLeft() == null) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
    }

    private static Node<Integer> getOrDefault(Node<Integer>[] nodes, int value) {
        if (nodes[value] == null) {
            nodes[value] = new Node<>(value);
        }
        return nodes[value];
    }

    public static Node<Integer> removeHalfNodes(Node<Integer> root) {
        if (root == null) {
            return null;
        }
        root.setLeft(removeHalfNodes(root.getLeft()));
        root.setRight(removeHalfNodes(root.getRight()));

        if (BinaryTree.hasOnlyOneChild(root)) {
            return root.getLeft() != null ? root.getLeft() : root.getRight();
        }

        return root;
    }

    public static int findLargestBSTSize(Node<Integer> root) {
        if (root == null) {
            return 0;
        }
        Map<Node<Integer>, Integer> minimum = new HashMap<>();
        Map<Node<Integer>, Integer> maximum = new HashMap<>();
        return findLargestBSTSize(root, minimum, maximum);
    }

    private static int findLargestBSTSize(Node<Integer> root, Map<Node<Integer>, Integer> minimum, Map<Node<Integer>, Integer> maximum) {
        if (root == null) {
            return 0;
        }
        int leftSize = findLargestBSTSize(root.getLeft(), minimum, maximum);
        int rightSize = findLargestBSTSize(root.getRight(), minimum, maximum);

        determineBSTOnCurrentLevel(root, minimum, maximum);
        return minimum.containsKey(root) ? (1 + leftSize + rightSize) : Math.max(leftSize, rightSize);
    }

    private static void determineBSTOnCurrentLevel(Node<Integer> root, Map<Node<Integer>, Integer> minimum, Map<Node<Integer>, Integer> maximum) {
        if (BinaryTree.isLeaf(root)) {
            minimum.put(root, root.getData());
            maximum.put(root, root.getData());
            return;
        }
        if (!isBST(root.getLeft(), minimum, maximum) || !isBST(root.getRight(), minimum, maximum)) {
            return;
        }

        int leftLargest = root.getLeft() == null ? Integer.MIN_VALUE : maximum.get(root.getLeft());
        int rightSmallest = root.getRight() == null ? Integer.MAX_VALUE : minimum.get(root.getRight());

        if (leftLargest < root.getData() && root.getData() < rightSmallest) {
            minimum.put(root, root.getLeft() == null ? root.getData() : minimum.get(root.getLeft()));
            maximum.put(root, root.getRight() == null ? root.getData() : maximum.get(root.getRight()));
        }
    }

    private static boolean isBST(Node<Integer> node, Map<Node<Integer>, Integer> minimum, Map<Node<Integer>, Integer> maximum) {
        return node == null || minimum.containsKey(node);
    }

}
