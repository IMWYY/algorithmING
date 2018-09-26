package leetCode.string;

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
        System.out.println(calculate("1-2+3-1"));
    }

    public static int calculate(String s) {
        s = s.trim();
        if (s.equals("")) return 0;

        int operandA = -1, operandB = -1;
        boolean isMulti = false, isDivison = false;
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
            } else if (c == '+') {
                return operandA + calculate(s.substring(i + 1));
            } else if (c == '-') {
                String temp = s.substring(i + 1);
                return operandA - calculate();
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
                    if (i >= s.length()) break;
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

//    public static int calculate(String s, boolean negative) {
//        s = s.trim();
//        if (s.equals("")) return 0;
//
//        int operandA = -1, operandB = -1;
//        boolean isMulti = false, isDivison = false;
//        for (int i = 0; i < s.length(); ) {
//            char c = s.charAt(i);
//            if (c == ' ') {
//                i++;
//            } else if (c == '+') {
//                System.out.println(negative + " " + operandA +  " + ");
//                if (negative) {
//                    return operandA - calculate(s.substring(i + 1), true);
//                }
//                return operandA + calculate(s.substring(i + 1), false);
//            } else if (c == '-') {
//                System.out.println(negative + " " + operandA + " - " );
//                if (negative) {
//                    return operandA - calculate(s.substring(i + 1), true);
//                }
//                return operandA - calculate(s.substring(i + 1), true);
//            } else if (c == '*') {
//                isMulti = true;
//                i++;
//            } else if (c == '/') {
//                isDivison = true;
//                i++;
//            } else {
//                int start = i;
//                while (c >= '0' && c <= '9') {
//                    i++;
//                    if (i >= s.length()) break;
//                    c = s.charAt(i);
//                }
//                int num = Integer.valueOf(s.substring(start, i));
//                if (operandA == -1) {
//                    operandA = num;
//                } else if (operandB == -1) {
//                    operandB = num;
//                    if (isMulti) {
//                        operandA *= operandB;
//                        operandB = -1;
//                        isMulti = false;
//                    } else if (isDivison) {
//                        operandA /= operandB;
//                        operandB = -1;
//                        isDivison = false;
//                    }
//                }
//            }
//        }
//        return operandA;
//    }
}
