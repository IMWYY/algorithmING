package leetCode;

/**
 * Reverse a singly linked list.
 * <p>
 * Example:
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * <p>
 * Follow up:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 * create by stephen on 2018/5/14
 */
public class Problem206 {

    /**
     * 递归法
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode tail = reverseList(head.next);
        ListNode temp = head.next;
        temp.next = head;
        head.next = null;
        return tail;
    }

    /**
     * 非递归法, 头指针插入法
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

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
