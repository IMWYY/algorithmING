package leetCode.string;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 * <p>
 * Example 1:
 * Input: "3+2*2"
 * Output: 7
 * <p>
 * Example 2:
 * Input: " 3/2 "
 * Output: 1
 * <p>
 * Example 3:
 * Input: " 3+5 / 2 "
 * Output: 5
 * create by stephen on 2018/9/26
 */
public class Problem227 {

	public static void main(String[] args) {
		System.out.println(new Problem227().calculate("1-1-1*4"));
	}

	/**
	 * 利用栈 将所有的符号都去除后放入stack 然后进行累加即可
	 */
public int calculate(String s) {
	Stack<Integer> stack = new Stack<>();
	char sign = '+';
	for (int i = 0; i < s.length(); i++) {
		char c = s.charAt(i);
		if (Character.isDigit(c)) {
			int start = i;
			while (c >= '0' && c <= '9') {
				i++;
				if (i >= s.length())
					break;
				c = s.charAt(i);
			}
			int operand = Integer.valueOf(s.substring(start, i));
			i--;
			if (sign == '+') {
				stack.push(operand);
			} else if (sign == '-') {
				stack.push(-operand);
			} else if (sign == '*') {
				stack.push(stack.pop() * operand);
			} else if (sign == '/') {
				stack.push(stack.pop() / operand);
			}
		} else if (c != ' ') {
			sign = c;
		}
	}
	int res = 0;
	for (int i : stack) {
		res += i;
	}
	return res;
}

	/**
	 * 递归方式分解+- 和 /*
	 * 需要对减法特殊考虑
	 * 不那么优雅的方法 而且time limit exceed
	 */
	public int calculate1(String s) {
		return calculate(s, false);
	}

	public int calculate(String s, boolean negative) {
		s = s.trim();
		if (s.equals(""))
			return 0;

		int operandA = -1, operandB = -1;
		boolean isMulti = false, isDivison = false;
		for (int i = 0; i < s.length(); ) {
			char c = s.charAt(i);

			if (negative) {
				if (c == '+') {
					c = '-';
				} else if (c == '-') {
					c = '+';
				}
			}

			if (c == ' ') {
				i++;
			} else if (c == '+') {
				return operandA + calculate(s.substring(i + 1), negative);
			} else if (c == '-') {
				return operandA - calculate(s.substring(i + 1), !negative);
			} else if (c == '*') {
				isMulti = true;
				i++;
			} else if (c == '/') {
				isDivison = true;
				i++;
			} else {
				int start = i;
				while (c >= '0' && c <= '9') {
					i++;
					if (i >= s.length())
						break;
					c = s.charAt(i);
				}
				int num = Integer.valueOf(s.substring(start, i));
				if (operandA == -1) {
					operandA = num;
				} else if (operandB == -1) {
					operandB = num;
					if (isMulti) {
						operandA *= operandB;
						operandB = -1;
						isMulti = false;
					} else if (isDivison) {
						operandA /= operandB;
						operandB = -1;
						isDivison = false;
					}
				}
			}
		}
		return operandA;
	}
}
