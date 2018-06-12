package leetCode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * Note: Do not modify the linked list.
 * Follow up:Can you solve it without using extra space?
 * <p>
 * create by stephen on 2018/4/27
 */
public class Problem142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        boolean isCycle = false;

        // check whether exist a cycle
        while (fast != null && slow != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) return null;
            fast = fast.next;
            if (fast == slow) {
                isCycle = true;
                break;
            }
        }

        if (!isCycle) return null;
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
