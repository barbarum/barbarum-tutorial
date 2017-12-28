package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Node<Integer> currentNode = getOrConstruct(nodes, i);
            if (parents[i] != -1) {
                Node<Integer> parentNode = getOrConstruct(nodes, parents[i]);
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

    private static Node<Integer> getOrConstruct(Node<Integer>[] nodes, int value) {
        if (nodes[value] == null) {
            nodes[value] = new Node<Integer>(value);
        }
        return nodes[value];
    }
}
