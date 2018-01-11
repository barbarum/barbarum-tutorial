package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.List;

public class BinaryTreeUtil {

    /**
     * Binary Tree - Print Tree in nature order
     */
    public static <T extends Comparable<T>> void printTree(Node<T> root) {
        List<T> result = BinaryTree.traversalLDR(root);

        StringBuilder builder = new StringBuilder();
        result.forEach((item) -> builder.append(item).append(" "));
        builder.trimToSize();
        System.out.println(builder.toString());
    }
}
