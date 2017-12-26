package com.barbarum.tutorial.code;

import com.barbarum.tutorial.code.tree.BinarySearchTreeQuestions;
import com.barbarum.tutorial.code.tree.BinaryTreeQuestions;
import com.barbarum.tutorial.code.tree.Node;
import com.barbarum.tutorial.util.PrintUtil;

public class TestBinarySearchTreeQuestions extends BasicTestCase {


    public void testIdenticalToBuildBST() {
        int[] tree1 = new int[]{3, 5, 4, 6, 1, 0, 2};
        int[] tree2 = new int[]{3, 1, 5, 2, 4, 6, 0};

        PrintUtil.println("Tree 1 -> ", tree1);
        PrintUtil.println("Tree 2 -> ", tree2);
        assertTrue(BinarySearchTreeQuestions.identical(tree1, tree2));
    }

    public void testIdenticalToBuildBST2() {
        int[] tree1 = new int[]{6, 9, 8};
        int[] tree2 = new int[]{6, 8, 9};

        PrintUtil.println("Tree 1 -> ", tree1);
        PrintUtil.println("Tree 2 -> ", tree2);
        assertFalse(BinarySearchTreeQuestions.identical(tree1, tree2));
    }

}
