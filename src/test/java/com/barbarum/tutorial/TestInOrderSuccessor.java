package com.barbarum.tutorial;

import com.barbarum.tutorial.code.tree.InOrderSuccessor;
import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Assert;
import org.junit.Test;

public class TestInOrderSuccessor {

    @Test
    public void testSearch() {
        Node<Integer> root = new Node<Integer>(3);
        root.setLeft(0).setLeft(1);
        root.getLeft().setRight(4).setRight(6).setLeft(5);
        root.setRight(2).setRight(7);

        Assert.assertEquals(root.getLeft().getRight().getRight().getLeft(), InOrderSuccessor.search(root.getLeft().getRight()));
        Assert.assertEquals(root, InOrderSuccessor.search(root.getLeft().getRight().getRight()));
        Assert.assertEquals(root.getLeft(), InOrderSuccessor.search(root.getLeft().getLeft()));
        Assert.assertEquals(root.getRight().getRight(), InOrderSuccessor.search(root.getRight()));
        Assert.assertNull(InOrderSuccessor.search(root.getRight().getRight()));
    }

    @Test
    public void testSearch2() {
        Node<Integer> root = new Node<Integer>(3);
        root.setLeft(0).setLeft(1);
        root.getLeft().setRight(4).setRight(6).setLeft(5);
        root.setRight(2).setRight(7);

        Assert.assertEquals(root.getLeft().getRight().getRight().getLeft(), InOrderSuccessor.search(root, root.getLeft().getRight()));
        Assert.assertEquals(root, InOrderSuccessor.search(root, root.getLeft().getRight().getRight()));
        Assert.assertEquals(root.getLeft(), InOrderSuccessor.search(root, root.getLeft().getLeft()));
        Assert.assertEquals(root.getRight().getRight(), InOrderSuccessor.search(root, root.getRight()));
        Assert.assertNull(InOrderSuccessor.search(root, root.getRight().getRight()));
    }
}
