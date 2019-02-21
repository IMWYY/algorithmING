package leetCode.linkedList;

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
 * <p>
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

    /**
     * 归并排序
     * 将前后两个链表先断开,然后merge时候按照大小顺序排序
     * O(nlogn) time + O(1) space
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;            //关键代码在这里 将前后两个链表先断开

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return mergeList(l1, l2);
    }

    private ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return dummy.next;
    }

    /**
     * 链表快速排序
     * O(nlogn) time + O(1) space
     */
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

    /**
     * 因为链表不能从后往前 所以与普通的快排相比 两个指针都只能从前往后移动
     * 两个指针p，q一起往后走，保持p之前小于flag，p与q之间大于flag
     * q每次遇到一个比flag小的元素就和p交换
     */
    private ListNode getSeparator(ListNode head, ListNode end) {
        ListNode p = head, q = head;
        while (q != end) {
            if (q.val < head.val) {
                p = p.next;
                // 交换p和q 只是交换值
                int temp = p.val;
                p.val = q.val;
                q.val = temp;
            }
            q = q.next;
        }
        // 交换head和p 只是交换值
        int temp = p.val;
        p.val = head.val;
        head.val = temp;

        return p;   // 返回flag
    }
}
