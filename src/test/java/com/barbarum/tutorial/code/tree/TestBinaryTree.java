package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestBinaryTree {

    @Test
    public void testPrintPath() {
        Node<Integer> root = new Node<Integer>(1);
        root.setChildren(2, 3);
        root.getLeft().setChildren(4, 5);
        root.getRight().setChildren(6, 7);

        List<List<Integer>> result = new ArrayList<>();
        BinaryTree.collectPaths(root, result);
        System.out.println(result);
    }
}
