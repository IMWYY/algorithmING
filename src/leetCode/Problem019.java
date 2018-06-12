package leetCode;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * <p>
 * Note: Given n will always be valid.
 * Follow up: Could you do this in one pass?
 * <p>
 * create by stephen on 2018/5/16
 */
public class Problem019 {

    /**
     * 两个指针 一次遍历 需要注意边界条件
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0 || head == null) return head;
        if (head.next == null) return null;

        ListNode slow = head, fast = head;
        int num = n;
        while (num > 0 && fast != null) {
            fast = fast.next;
            num--;
        }

        if (fast == null) return head.next;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (slow.next != null) {
            slow.next = slow.next.next;
        }

        return head;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
