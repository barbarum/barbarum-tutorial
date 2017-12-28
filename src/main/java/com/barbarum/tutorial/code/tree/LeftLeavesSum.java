package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

public class LeftLeavesSum {

    public static int sum(Node<Integer> root) {
        if (root == null || isLeaf(root)) {
            return 0;
        }

        int sum = 0;

        if (root.getLeft() != null) {
            sum += isLeaf(root.getLeft()) ? root.getLeft().getData() : sum(root.getLeft());
        }

        if (root.getRight() != null) {
            sum += isLeaf(root.getRight()) ? 0 : sum(root.getRight());
        }

        return sum;
    }

    private static boolean isLeaf(Node<Integer> root) {
        return root.getLeft() == null && root.getRight() == null;
    }

}
