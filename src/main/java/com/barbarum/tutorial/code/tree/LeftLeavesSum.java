package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

public class LeftLeavesSum {

    public static int sum(Node<Integer> root) {
        if (root == null || BinaryTree.isLeaf(root)) {
            return 0;
        }

        int sum = 0;

        if (root.getLeft() != null) {
            sum += BinaryTree.isLeaf(root.getLeft()) ? root.getLeft().getData() : sum(root.getLeft());
        }

        if (root.getRight() != null) {
            sum += BinaryTree.isLeaf(root.getRight()) ? 0 : sum(root.getRight());
        }

        return sum;
    }

}
