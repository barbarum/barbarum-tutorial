package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

public class BinarySearchTreeRecover {

    public static void recover(Node<Integer> root) {
        Node<Integer>[] nodes = new Node[3];
        inOrderTraversal(root, nodes);
        swap(nodes);
    }

    private static void inOrderTraversal(Node<Integer> root, Node<Integer>[] nodes) {
        if (root == null || found(nodes)) {
            return;
        }
        inOrderTraversal(root.getLeft(), nodes);

        Node<Integer> previousNode = nodes[2];
        if (previousNode != null && previousNode.getData() > root.getData()) {
            if (nodes[0] == null) {
                nodes[0] = previousNode;
            } else {
                nodes[1] = root;
            }
        }
        nodes[2] = root;
        
        inOrderTraversal(root.getRight(), nodes);
    }

    private static boolean found(Node<Integer>[] nodes) {
        return nodes[0] != null && nodes[1] != null;
    }

    private static void swap(Node<Integer>[] nodes) {
        if (!found(nodes)) {
            return;
        }
        int temp = nodes[0].getData();
        nodes[0].setData(nodes[1].getData());
        nodes[1].setData(temp);
    }
}
