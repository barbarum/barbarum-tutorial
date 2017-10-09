package com.barbarum.tutorial.hacker;

public class BinarySearchTree {

    public static boolean checkBST(Node root) {
        return root == null || checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean checkBST(Node node, long min, long max) {
        if (node == null) {
            return true;
        }

        if (node.data <= min && node.data <= max) {
            return false;
        }
        
        return checkBST(node.left, min, node.data) && checkBST(node.right, node.data, max);

    }

    private class Node {
        int data;
        Node left;
        Node right;
    }
}
