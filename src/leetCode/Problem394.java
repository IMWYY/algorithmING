package leetCode;

import java.util.Stack;

/**
 * Given an encoded string, return it's decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is
 * being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * <p>
 * Furthermore, you may assume that the original data does not contain any digits and that digits
 * are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * <p>
 * Examples:
 * <p>
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 * create by stephen on 2018/6/3
 */
public class Problem394 {

    /**
     * 递归+模拟栈 将[]里的字符串继续递归
     */
    public String decodeString(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int parentheses = 0, startIndex = 0, times = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (parentheses == 0) {
                    int cur = i;                // 这里是为了计算多位数的数字值
                    while (s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9') {
                        i ++;                   // 注意i的下标 容易出错
                    }
                    times = Integer.valueOf(s.substring(cur, i+1));
                }
            } else if (c == '[') {
                if (parentheses == 0) {
                    startIndex = i;
                }
                parentheses++;
            } else if (c == ']') {
                parentheses--;
                if (parentheses == 0) {
                    for (int j = 0; j < times; ++j) {
                        stringBuilder.append(decodeString(s.substring(startIndex + 1, i)));
                    }
                }
            } else {
                if (parentheses == 0) {     // 如果当前位置不再[]里面 则将字符直接加入到结果里面
                    stringBuilder.append(c);
                }                           // 如果在[]里面 里面的字符不能直接加到builder里面
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 栈解决
     * 遇到数字压栈
     * 遇到[压栈到目前位置的字符串
     * 遇到]出栈数字和字符串 拼接字符串
     */
    public String decodeString1(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        String res = "";
        int index = 0;
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count += count * 10 + s.charAt(index) - '0';
                    index ++;
                }
                countStack.push(count);
            } else if (s.charAt(index) == '[') {
                stringStack.push(res);
                res = "";
                index ++;
            } else if (s.charAt(index) == ']') {
                StringBuilder builder = new StringBuilder(stringStack.pop());
                int count = countStack.pop();
                for (int i=0; i<count; ++i) {
                    builder.append(res);
                }
                res = builder.toString();
                index ++;
            } else {
                res += s.charAt(index ++);
            }
        }
        return res;
    }
}
