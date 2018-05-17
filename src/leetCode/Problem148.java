package leetCode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * <p>
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 * <p>
 *
 * create by stephen on 2018/5/17
 */
public class Problem148 {

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /***********************************************************
     * 归并排序
     **********************************************************/
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;            //关键代码在这里

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return mergeList(l1, l2);
    }

    private ListNode mergeList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val > l2.val) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }

        ListNode cur1 = l1, cur2 = l2, pre1 = null, temp;
        while (cur2 != null) {
            while (cur1 != null && cur1.val <= cur2.val) {
                pre1 = cur1;
                cur1 = cur1.next;
            }
            if (cur1 == null) break;

            temp = cur2.next;
            if (pre1 != null) pre1.next = cur2;
            cur2.next = cur1;

            cur2 = temp;
        }

        if (cur1 == null) {
            pre1.next = cur2;
        }

        return l1;
    }


    /*********************************************************
     * 链表快速排序
     * 两个指针p，q一起往后走，保持p之前小于flag，p与q之间大于flag
     **********************************************************/
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) return head;
        return quickSort(head, null);
    }

    private ListNode quickSort(ListNode head, ListNode end) {
        if (head != null && head != end) {
            ListNode temp = getSeparator(head, end);
            quickSort(head, temp);
            quickSort(temp.next, end);
        }
        return head;
    }

    private ListNode getSeparator(ListNode head, ListNode end) {
        ListNode p = head, q = head;
        while (q != end) {
            if (q.val < head.val) {
                p = p.next;
                // 交换p和q
                int temp = p.val;
                p.val = q.val;
                q.val = temp;
            }
            q = q.next;
        }

        // 交换head和p
        int temp = p.val;
        p.val = head.val;
        head.val = temp;

        return p;
    }
}
