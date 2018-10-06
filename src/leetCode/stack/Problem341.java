package leetCode.stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * <p>
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 * create by stephen on 2018/10/6
 */
public class Problem341 {

    private interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    /**
     * 类似于树的中序遍历 可以利用栈
     * 相比递归 用栈不是直接遍历所有结果保存起来 而是每次hasnext来push和pop
     */
    public class NestedIterator implements Iterator<Integer> {

        private Stack<NestedInteger> stack = new Stack<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger cur = stack.peek();
                if (cur.isInteger()) return true;
                cur = stack.pop();
                List<NestedInteger> list = cur.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
            }
            return false;
        }
    }

    /**
     * 类似于树的中序遍历 可以用递归解决
     */
    public class NestedIterator1 implements Iterator<Integer> {

        private List<Integer> list = new LinkedList<>();
        private int index;

        public NestedIterator1(List<NestedInteger> nestedList) {
            for (NestedInteger aNestedList : nestedList)
                this.constructList(aNestedList);
        }

        private void constructList(NestedInteger nestedInteger) {
            if (nestedInteger.isInteger()) {
                list.add(nestedInteger.getInteger());
            } else {
                List<NestedInteger> integers = nestedInteger.getList();
                for (NestedInteger integer : integers) {
                    this.constructList(integer);
                }
            }
        }

        @Override
        public Integer next() {
            if (index >= list.size()) return 0;
            return list.get(index++);
        }

        @Override
        public boolean hasNext() {
            return index < list.size();
        }
    }
}
