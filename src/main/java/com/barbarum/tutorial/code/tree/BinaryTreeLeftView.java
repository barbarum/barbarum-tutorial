package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeLeftView {

    public static List<Node<Integer>> search(Node<Integer> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Node<Integer>> result = new ArrayList<>();
        search(root, 0, result);
        return result;
    }

    private static void search(Node<Integer> root, int depth, List<Node<Integer>> result) {
        if (root == null) {
            return;
        }
        if (depth == result.size()) {
            result.add(root);
        }
        search(root.getLeft(), depth + 1, result);
        search(root.getRight(), depth + 1, result);
    }
}
