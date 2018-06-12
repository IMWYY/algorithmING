package leetCode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 * <p>
 * A:       a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * B:  b1 → b2 → b3
 * begin to intersect at node c1.
 * <p>
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 * <p>
 * create by stephen on 2018/5/16
 */
public class Problem160 {
    /**
     * 不需要知道每个链表的长度 只需要两个指针同时遍历
     * 如果A达到末尾 就重新跳转到B的head。两者相遇的地方就是交点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }

    /**
     * 找到链表的长度 然后从长度一样的地方一起往下遍历
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode temp = headA;
        int lenA = 0, lenB = 0;
        while (temp != null) {
            lenA++;
            temp = temp.next;
        }

        temp = headB;
        while (temp != null) {
            lenB++;
            temp = temp.next;
        }

        if (lenA > lenB) { //交换 使a的长度是短的
            int t = lenA;
            lenA = lenB;
            lenB = t;

            temp = headA;
            headA = headB;
            headB = temp;
        }

        temp = headB;
        while (lenB > lenA) {
            temp = temp.next;
            lenB--;
        }

        while (temp != null) {
            if (temp == headA) return temp;
            temp = temp.next;
            headA = headA.next;
        }

        return null;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
