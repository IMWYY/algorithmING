package leetCode.stack;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Note:
 * <p>
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always
 * evaluate to a result and there won't be any divide by zero operation.
 */
public class Problem150 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            switch (s) {
                case "+": {
                    int tmp = stack.pop() + stack.pop();
                    stack.push(tmp);
                    break;
                }
                case "-": {
                    int op2 = stack.pop();
                    int op1 = stack.pop();
                    int tmp = op1 - op2;
                    stack.push(tmp);
                    break;
                }
                case "*": {
                    int tmp = stack.pop() * stack.pop();
                    stack.push(tmp);
                    break;
                }
                case "/": {
                    int op2 = stack.pop();
                    int op1 = stack.pop();
                    int tmp = op1 / op2;
                    stack.push(tmp);
                    break;
                }
                default:
                    stack.push(Integer.valueOf(s));
                    break;
            }
        }

        return stack.pop();
    }
}
