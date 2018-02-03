package com.barbarum.tutorial.code.other;

import com.barbarum.tutorial.code.list.LinkedList;
import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Assert;
import org.junit.Test;

public class TestLinkedList {

    @Test
    public void testDetect() {
        Assert.assertNull(LinkedList.detect(null));
        Assert.assertNull(LinkedList.detect(new Node<Integer>(1)));

        Node<Integer> nonLooped = new Node<Integer>(1);
        nonLooped.setRight(2).setRight(3);
        Assert.assertNull(LinkedList.detect(nonLooped));

        Node<Integer> looped = new Node<Integer>(1);
        Node<Integer> tail = looped.setRight(3).setRight(5).setRight(7).setRight(9).setRight(4).setRight(8);
        Node<Integer> pivot = looped.getRight().getRight().getRight();
        tail.setRight(pivot);
        Assert.assertEquals(pivot, LinkedList.detect(looped));
    }
}
