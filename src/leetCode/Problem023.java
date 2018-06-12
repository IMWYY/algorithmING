package leetCode;


/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * create by stephen on 2018/5/17
 */
public class Problem023 {

    /**
     * 归并法合并列表 可以用递归法 这里用了非递归
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        int interval = 1;
        while (interval < lists.length) {
            for (int i = 0; i < lists.length - interval; i += interval * 2) {
                lists[i] = mergeTwoList(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    /**
     * 一个个合并
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; ++i) {
            result = mergeTwoList(result, lists[i]);
        }
        return result;
    }

    @SuppressWarnings("all")
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
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
            pre1 = cur2;

            cur2 = temp;
        }

        if (cur2 != null) {
            pre1.next = cur2;
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
