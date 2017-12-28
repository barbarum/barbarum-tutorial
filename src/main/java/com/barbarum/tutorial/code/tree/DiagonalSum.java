package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiagonalSum {

    public static List<Integer> sum(Node<Integer> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();

        sum(root, 0, result);

        return result;
    }

    private static void sum(Node<Integer> root, int diagonal, List<Integer> result) {
        if (root == null) {
            return;
        }

        if (diagonal == result.size()) {
            result.add(root.getData());
        } else {
            result.set(diagonal, result.get(diagonal) + root.getData());
        }
        sum(root.getLeft(), diagonal + 1, result);
        sum(root.getRight(), diagonal, result);
    }
}
