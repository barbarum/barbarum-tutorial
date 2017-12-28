package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;

import java.util.List;
import java.util.Optional;

/**
 * https://en.wikipedia.org/wiki/Binary_search_tree#Insertion
 * <p>
 * Definition:
 * whose internal nodes each store a key (and optionally, an associated value) and each have two distinguished sub-trees, commonly denoted left and right.
 * The tree additionally satisfies the binary lookup tree property, which states that the key in each node must be greater than or equal to any key stored in the left sub-tree, and less than or equal to any key stored in the right sub-tree.
 */
public class BinarySearchTree extends BinaryTree {

    /**
     * Binary Search Tree -> Lookup
     *
     * @param root   root node
     * @param target the searched data
     * @param <T>    Generalized type
     * @return true if found, otherwise false.
     */
    public static <T extends Comparable<T>> boolean lookup(Node<T> root, T target) {
        if (root == null) {
            return false;
        }
        int result = target.compareTo(root.getData());
        return result == 0 || lookup(result < 0 ? root.getLeft() : root.getRight(), target);
    }

    /**
     * Binary Search Tree -> Insert (Unbalanced)
     * <p>
     * Node: this method doesn't offer balance factor to the new tree.
     * So the drawback is the tree might become LinkedList in the worst situation.
     *
     * @param root   root node
     * @param target the inserted data
     * @param <T>    generalized type.
     */
    public static <T extends Comparable<T>> Node<T> insert(Node<T> root, T target) {
        if (root == null) {
            return new Node<T>(target);
        }
        int result = target.compareTo(root.getData());
        if (result <= 0) {
            root.setLeft(insert(root.getLeft(), target));
        } else {
            root.setRight(insert(root.getRight(), target));
        }

        return root;
    }

    /**
     * Binary Search Tree -> Build (balanced)
     * <p>
     * The method would try making the tree as balanced as possible, as complete as possible.
     * </p>
     *
     * @param data data array
     * @param <T>  Generalized type.
     * @return the new built binary lookup tree.
     */
    public static <T extends Comparable<T>> Node<T> buildBST(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return buildBST(data, 0, data.length - 1);
    }

    private static <T extends Comparable<T>> Node<T> buildBST(T[] data, int start, int end) {
        if (start > end || start >= data.length || end < 0) {
            return null;
        }
        int median = (start + end + 1) / 2;
        return new Node<T>(data[median], buildBST(data, start, median - 1), buildBST(data, median + 1, end));
    }

    /**
     * Binary Search Tree - Find the smallest element from the given tree.
     *
     * @param root root node.
     * @param <T>  generalized type.
     * @return minimum data, or null if the tree is empty.
     */
    public static <T extends Comparable<T>> T findSmallestElement(Node<T> root) {
        if (root == null) {
            return null;
        }
        return Optional.ofNullable(findSmallestElement(root.getLeft()))
                .filter(item -> item.compareTo(root.getData()) <= 0)
                .orElse(root.getData());
    }

    /**
     * Examine if the given tree is a binary lookup tree.
     *
     * @param root the given root node.
     * @param <T>  generalized type.
     * @return true if it's, otherwise false.
     */
    public static <T extends Comparable<T>> boolean examine(Node<T> root) {
        return root == null || examine(root, null, null);
    }

    private static <T extends Comparable<T>> boolean examine(Node<T> root, T minData, T maxData) {
        // examine left child is less or equals to root node's data if exists.
        if (minData != null && minData.compareTo(root.getData()) > 0) {
            return false;
        }

        // examine right child is greater than root node's data if exists.
        if (maxData != null && maxData.compareTo(root.getData()) <= 0) {
            return false;
        }

        return examine(root.getLeft(), minData, root.getData())
                && examine(root.getRight(), root.getData(), maxData);
    }

    /**
     * Convert a binary lookup tree to double linked list.
     *
     * @param root the given root node.
     * @param <T>  generalized type.
     * @return head node
     */
    public static <T extends Comparable<T>> Node<T> convert(Node<T> root) {
        Node<T> head = new Node<T>(null);
        convert(root, head);
        if (head.getRight() != null) {
            head.getRight().setLeft((Node<T>) null);
        }
        return head.getRight();
    }

    private static <T extends Comparable<T>> Node<T> convert(Node<T> root, Node<T> tail) {
        if (root == null) {
            return tail;
        }

        tail = convert(root.getLeft(), tail);

        tail.setRight(root);
        root.setLeft(tail);
        tail = root;

        tail = convert(root.getRight(), tail);

        return tail;
    }

    /**
     * Find the largest data in the tree which is less than or equals to the target.
     */
    public static <T extends Comparable<T>> Optional<T> floor(Node<T> root, T target) {
        if (root == null) {
            return Optional.empty();
        }
        int diff = root.getData().compareTo(target);
        // if the root data is equals to target, then we found it.
        if (diff == 0) {
            return Optional.of(root.getData());
        }
        // if root data is larger than target, try to find from the left child.
        if (diff > 0) {
            return floor(root.getLeft(), target);
        }
        // if the root data is smaller than target,
        // then we look for if any data exists from the right child otherwise we return the root data.
        T result = floor(root.getRight(), target)
                .orElse(root.getData());

        return Optional.of(result);
    }

    /**
     * Find the smallest data in the tree which is greater than or equals to the target.
     */
    public static <T extends Comparable<T>> Optional<T> ceiling(Node<T> root, T target) {
        if (root == null) {
            return Optional.empty();
        }
        int diff = root.getData().compareTo(target);
        // if the root data is equals to the target, then we found it.
        if (diff == 0) {
            return Optional.of(root.getData());
        }
        // if the root data is smaller than the target, then look for the data from the right child.
        if (diff < 0) {
            return ceiling(root.getRight(), target);
        }
        // if the root data is greater than the target,
        // then we look for whether any data can be found from the right child, otherwise we return the root data.
        T result = ceiling(root.getLeft(), target)
                .orElse(root.getData());
        
        return Optional.of(result);
    }
}
