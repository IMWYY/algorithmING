package leetCode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 * <p>
 * Return a deep copy of the list.
 * create by stephen on 2018/9/6
 */
public class Problem138 {
    private class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    /**
     * O(n) space + O(n) time
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode temp = head;
        while (temp != null) {
            RandomListNode node = new RandomListNode(temp.label);
            map.put(temp, node);
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            RandomListNode node = map.get(temp);
            if (temp.next != null) {
                node.next = map.get(temp.next);
            }
            if (temp.random != null) {
                node.random = map.get(temp.random);
            }
            temp = temp.next;
        }

        return map.get(head);
    }

    /**
     * O(1) space + O(n) time
     */
    public RandomListNode copyRandomList1(RandomListNode head) {
        RandomListNode iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            RandomListNode copy = new RandomListNode(iter.label);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        RandomListNode pseudoHead = new RandomListNode(0);
        RandomListNode copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }
}
