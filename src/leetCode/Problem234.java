package leetCode;

import java.util.Stack;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Example 1:
 * Input: 1->2
 * Output: false
 * <p>
 * Example 2:
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 * <p>
 * create by stephen on 2018/5/15
 */
public class Problem234 {

    /**
     * 反转一半链表
     * O(n) time + O(1) space
     */
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }

        //反转另一半列表
        ListNode pre = slow, next;
        slow = slow.next;
        while (slow != null) {
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        slow = head;
        while (pre != null) {
            if (pre.val != slow.val) return false;
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 使用栈
     * O(n) time + O(n) space
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;
        ListNode slow = head, fast = head;
        Stack<Integer> stack = new Stack<>();
        while (fast != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                stack.pop();
            }
        }

        while (slow != null) {
            if (slow.val != stack.pop()) return false;
            slow = slow.next;
        }
        return true;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
