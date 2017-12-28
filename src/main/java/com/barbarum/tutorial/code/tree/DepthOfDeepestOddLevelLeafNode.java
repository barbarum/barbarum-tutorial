package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

public class DepthOfDeepestOddLevelLeafNode {

    public static int findDepth(Node<Integer> root) {
        return findDepth(root, 1);
    }

    private static int findDepth(Node<Integer> root, int currentDepth) {
        if (root == null) {
            return -1;
        }
        if (isLeaf(root)) {
            return currentDepth % 2 == 1 ? currentDepth : -1;
        }
        int leftDepth = findDepth(root.getLeft(), currentDepth + 1);
        int rightDepth = findDepth(root.getRight(), currentDepth + 1);
        return Math.max(leftDepth, rightDepth);
    }

    private static boolean isLeaf(Node<Integer> root) {
        return root.getLeft() == null && root.getRight() == null;
    }
}
