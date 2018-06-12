package leetCode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 * <p>
 * create by stephen on 2018/5/15
 */
public class Problem021 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode cur1 = l1, cur2 = l2, pre1 = null, temp2 = cur2.next;

        while (cur2 != null) {
            while (cur1 != null && cur1.val <= cur2.val) {
                pre1 = cur1;
                cur1 = cur1.next;
            }

            if (cur1 == null) break;

            if (pre1 != null) pre1.next = cur2;
            cur2.next = cur1;
            pre1 = cur2;                    //更新pre1

            cur2 = temp2;                   //更新cur2
            if (temp2 != null) temp2 = temp2.next;      //更新temp2
        }
        cur1 = pre1;
        if (cur2 != null) {
            cur1.next = cur2;
        }

        return (l1.val <= l2.val) ? l1 : l2;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
