package leetCode.linkedList;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * create by stephen on 2018/9/12
 */
public class Problem328 {

    /**
     * mine：需要注意空指针 每一步都要做检查
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode evenHead = head.next, odd = head, even = head.next;
        while (true) {
            ListNode temp = odd;
            odd = odd.next;
            if (odd == null) {
                even.next = null;
                odd = temp;
                break;
            } else {
                odd = odd.next;
                if (odd != null) {
                    even.next = temp.next; // 更新even
                    even = temp.next;

                    temp.next = odd;    // 更新odd
                } else {
                    even.next = temp.next;
                    odd = temp;
                    break;
                }

            }
        }

        odd.next = evenHead;
        return head;
    }

    /**
     * others：更简洁。对于各种空指针情况只要关注even节点即可，odd节点肯定不会为null
     */
    public ListNode oddEvenList1(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
