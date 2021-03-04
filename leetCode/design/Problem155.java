package leetCode.design;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class Problem155 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    private int min = Integer.MIN_VALUE;

    /**
     * use an additional stack to maintain the minimum element so far.
     */
    public Problem155() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        min = Math.min(min, x);
        minStack.push(min);
    }

    public void pop() {
        if (!stack.isEmpty()) stack.pop();
        if (!minStack.isEmpty()) minStack.pop();

        if (!minStack.isEmpty()) {
            min = minStack.peek();
        } else {
            min = Integer.MAX_VALUE;
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()) return 0;
        return minStack.peek();
    }
}