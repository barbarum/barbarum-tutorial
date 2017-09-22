package com.barbarum.tutorial.leetcode.pojo;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public String convertToString() {
        StringBuilder builder = new StringBuilder();

        ListNode current = this;
        while (current != null) {
            builder.append(current.val);
            current = current.next;
        }
        return builder.toString();
    }

    public static ListNode as(Integer... nums) {
        ListNode[] list = new ListNode[nums.length];

        for (int i = 0; i < nums.length; i++) {
            list[i] = new ListNode(nums[i]);
        }

        for (int i = 0; i < nums.length - 1; i++) {
            list[i].next = list[i + 1];
        }

        return list[0];
    }
}
