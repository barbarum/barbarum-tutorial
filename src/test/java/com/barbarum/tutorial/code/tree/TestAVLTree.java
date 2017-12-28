package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.AVLTree;
import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Test;

public class TestAVLTree {

    @Test
    public void testInsert() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println(tree.getRoot());
    }


    @Test
    public void testDelete() {
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 20; i > 0; i--) {
            tree.insert(i);
        }
        Node<Integer> root = tree.getRoot();
        System.out.println(root);
        tree.delete(17);
        tree.delete(18);
        tree.delete(19);
        tree.delete(20);
    }
}
