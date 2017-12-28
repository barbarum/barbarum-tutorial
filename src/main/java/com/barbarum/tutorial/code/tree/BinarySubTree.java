package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

public class BinarySubTree {

    public static boolean check(Node<Integer> tree1, Node<Integer> tree2) {
        if (tree2 == null) {
            return true;
        }
        if (tree1 == null) {
            return false;
        }
        Node<Integer> subTree = lookup(tree1, tree2.getData());
        return subTree != null
                && checkSameTree(subTree, tree2);

    }

    private static Node<Integer> lookup(Node<Integer> root, int target) {
        if (root == null) {
            return null;
        }
        if (root.getData() == target) {
            return root;
        }

        Node<Integer> node = lookup(root.getLeft(), target);
        if (node != null) {
            return node;
        }
        return lookup(root.getRight(), target);
    }


    private static boolean checkSameTree(Node<Integer> tree1, Node<Integer> tree2) {
        if (tree1 == null || tree2 == null) {
            return tree1 == tree2;
        }
        return tree1.getData().equals(tree2.getData())
                && checkSameTree(tree1.getLeft(), tree2.getLeft())
                && checkSameTree(tree1.getRight(), tree2.getRight());
    }

    public static void main(String args[]) {
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

        System.out.println(check(tree1, tree2));
        System.out.println(check(tree2, tree3));
    }
}
