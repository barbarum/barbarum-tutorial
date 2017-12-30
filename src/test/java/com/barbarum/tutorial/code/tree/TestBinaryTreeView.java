package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.barbarum.tutorial.code.tree.BinaryTreeView.*;

public class TestBinaryTreeView {

    @Test
    public void testLeft() {
        Node<Integer> root = new Node<Integer>(1);
        root.setLeft(2).setLeft(4).setRight(8).setRight(10);
        root.getLeft().setRight(5);
        root.setRight(3).setLeft(6).setRight(9);

        Assert.assertEquals(Arrays.asList(1, 2, 4, 8, 10), left(root));
    }

    @Test
    public void testRight() {
        Node<Integer> root = new Node<Integer>(1);
        root.setLeft(2).setLeft(4).setRight(8).setRight(10);
        root.getLeft().setRight(5);
        root.setRight(3).setLeft(6).setRight(9);

        Assert.assertEquals(Arrays.asList(1, 3, 6, 9, 10), right(root));
    }

    @Test
    public void testBottom() {
        Node<Integer> root = new Node<Integer>(1);
        root.setChildren(2, 3);
        root.getLeft().setChildren(4, 5);
        root.getRight().setChildren(6, 7);
        Assert.assertArrayEquals(new int[]{4, 2, 6, 3, 7}, bottomV2(root));

        Node<Integer> test2 = new Node<Integer>(1);
        test2.setChildren(2, 3);
        test2.getLeft().setChildren(4, 5);
        test2.getRight().setChildren(6, 7);
        test2.getLeft().getLeft().setRight(8);
        test2.getLeft().getRight().setLeft(9);
        test2.getRight().getLeft().setRight(10);
        test2.getRight().getRight().setRight(11);
        Assert.assertArrayEquals(new int[]{4, 9, 6, 10, 7, 11}, bottomV2(test2));
    }


    @Test
    public void testTop() {
        Node<Integer> root = new Node<Integer>(1);
        root.setChildren(2, 3);
        root.getLeft().setChildren(4, 5);
        root.getRight().setChildren(6, 7);
        Assert.assertArrayEquals(new int[]{4, 2, 1, 3, 7}, top(root));

        Node<Integer> test2 = new Node<Integer>(1);
        test2.setChildren(2, 3);
        test2.getLeft().setChildren(4, 5);
        test2.getRight().setChildren(6, 7);
        test2.getLeft().getLeft().setRight(8);
        test2.getLeft().getRight().setLeft(9);
        test2.getRight().getLeft().setRight(10);
        test2.getRight().getRight().setRight(11);
        Assert.assertArrayEquals(new int[]{4, 2, 1, 3, 7, 11}, top(test2));
    }

    @Test
    public void testVertical() {
        Node<Integer> root = new Node<Integer>(3);
        root.setChildren(4, 5);
        root.getLeft().setChildren(6, 7);
        root.getRight().setChildren(8, 9);
        root.getLeft().getRight().setRight(1);


        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(6));
        expected.add(Arrays.asList(4));
        expected.add(Arrays.asList(3, 7, 8));
        expected.add(Arrays.asList(5, 1));
        expected.add(Arrays.asList(9));

        Assert.assertEquals(expected, vertical(root));
    }
}
