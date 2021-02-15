package leetCode.twoPoint;


import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 */
public class Problem023 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }

    /**
     * solution1: use a minheap to record the minimum value of current front elements of k lists
     * O(k*n*logk) time + O(k) space
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;

        for (ListNode node : lists)
            if (node != null) queue.add(node);

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null) queue.add(tail.next);
        }
        return dummy.next;
    }


    /**
     * solution 2: merge lists by different intervals
     * O(k*n*logk) time + O(1) space
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        int interval = 1;
        while (interval < lists.length) {
            for (int i = 0; i < lists.length - interval; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    /**
     * solution 3: merge lists one by one
     * O(k*n*logk) time + O(1) space
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; ++i) {
            result = mergeTwoLists(result, lists[i]);
        }
        return result;
    }

    @SuppressWarnings("all")
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
