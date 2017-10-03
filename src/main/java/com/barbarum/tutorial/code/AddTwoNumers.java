package com.barbarum.tutorial.code;

import com.barbarum.tutorial.code.pojo.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

public class AddTwoNumers {

    public static ListNode addTwoNumbers(ListNode a, ListNode b) {
        List<ListNode> list = new ArrayList<>();
        ListNode temp = new ListNode(0);

        ListNode l1Current = a;
        ListNode l2Current = b;
        int resultIndex = 0;

        while (l1Current != null && l2Current != null) {
            list.add(l1Current);
            temp.val += l1Current.val + l2Current.val;

            list.get(resultIndex).val = temp.val % 10;
            temp.val = temp.val / 10;

            l1Current = l1Current.next;
            l2Current = l2Current.next;
            resultIndex++;
        }

        ListNode resultCurrent = null;

        if (l1Current != null) {
            resultCurrent = l1Current;
        } else if (l2Current != null) {
            resultCurrent = l2Current;
        }

        while (temp.val > 0 || resultCurrent != null) {
            resultCurrent = resultCurrent == null ? new ListNode(0) : resultCurrent;

            list.add(resultCurrent);
            temp.val = resultCurrent.val + temp.val;

            list.get(resultIndex).val = temp.val % 10;
            temp.val = temp.val / 10;

            resultCurrent = resultCurrent.next;
            resultIndex++;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        return list.get(0);
    }

    public static ListNode addWithNewList(ListNode a, ListNode b) {
        ListNode l1 = a;
        ListNode l2 = b;

        ListNode head = new ListNode(0);
        ListNode current = head;

        int sum = 0;
        int carry = 0;

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            sum += carry;
            current.next = new ListNode(sum % 10);
            carry = sum / 10;

            sum = 0;

            current = current.next;
        }

        if (carry == 1) {
            current.next = new ListNode(carry);
        }

        return head.next;
    }
}
