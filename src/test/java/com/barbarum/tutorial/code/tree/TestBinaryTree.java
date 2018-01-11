package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Assert;
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

    @Test
    public void testCountBST() {
        Assert.assertEquals(5, BinaryTree.countTrees(3));
        Assert.assertEquals(42, BinaryTree.countTrees(5));
    }

    @Test
    public void testSubTree(){
        Node<Integer> tree1 = new Node<Integer>(0);
        tree1.setLeft(1).setLeft(3).setRight(6).setRight(8);
        tree1.getLeft().setRight(4);
        tree1.setRight(2).setLeft(5).setRight(7);

        Node<Integer> tree2 = new Node<Integer>(1);
        tree2.setLeft(3).setRight(6).setRight(8);
        tree2.setRight(4);

        Node<Integer> tree3 = new Node<Integer>(1);
        tree3.setLeft(3).setRight(6);
        tree3.setRight(4);

        System.out.println(BinaryTree.isSubTree(tree1, tree2));
        System.out.println(BinaryTree.isSubTree(tree2, tree3));
    }
}
