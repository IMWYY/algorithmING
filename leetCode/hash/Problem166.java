package leetCode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 */
public class Problem166 {

    public static void main(String[] args) {
        System.out.println(new Problem166().fractionToDecimal(-1, -2147483648));
    }

    /**
     * 模拟除法的计算方法
     * 先计算整数 然后计算小数部分
     * 当计算循环小数的时候，需要利用map记录循环的起始位置
     * 为了避免溢出 将integer转换为long类型
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append((numerator > 0) ^ (denominator > 0) ? '-' : "");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        sb.append(num / den);
        num %= den;
        if (num == 0) {
            return sb.toString();
        }
        sb.append('.');

        Map<Long, Integer> map = new HashMap<>();
        map.put(num, sb.length());

        // 余数
        while (num != 0) {
            num *= 10;
            sb.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                sb.insert(map.get(num), "(");
                sb.append(')');
                break;
            } else {
                map.put(num, sb.length());
            }
        }
        return sb.toString();
    }
}
