package leetCode.linkedList;

/**
 * Reverse a singly linked list.
 */
public class Problem206 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }

    /**
     * solution1: recursive
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode tail = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return tail;
    }

    /**
     * solution2: iterative
     */
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

    /**
     * solution3: insert node at head
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head.next, pre = head;
        ListNode temp = head.next.next;
        head.next = null;
        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = temp;
            if (temp != null) temp = temp.next;
        }
        return pre;
    }
}
