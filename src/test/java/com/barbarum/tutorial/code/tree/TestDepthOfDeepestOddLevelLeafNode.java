package com.barbarum.tutorial.code.tree;

import com.barbarum.tutorial.code.tree.DepthOfDeepestOddLevelLeafNode;
import com.barbarum.tutorial.code.tree.data.Node;
import org.junit.Assert;
import org.junit.Test;

public class TestDepthOfDeepestOddLevelLeafNode {

    @Test
    public void testNormalCase() {
        Node<Integer> root = new Node<Integer>(1);
        root.setLeft(2);
        root.setRight(3).setLeft(4).setLeft(6).setRight(8);
        root.getRight().setRight(5).setRight(7).setLeft(9).setLeft(10);

        Assert.assertEquals(5, DepthOfDeepestOddLevelLeafNode.findDepth(root));
    }
}
