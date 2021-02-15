package leetCode.twoPoint;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * Follow up: Could you do this in one pass?
 */
public class Problem019 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

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
        if (slow.next != null) slow.next = slow.next.next;

        return head;
    }
}
