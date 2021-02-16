package leetCode.array;

import java.util.Arrays;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 */
public class Problem179 {

    /**
     * 将数组转换为字符串排序 但是需要注意的是排序规则：
     * 如 3，30，34
     * 因为4>3, 0<3, 所以排序完的顺序为34，3，30
     *
     * 所以排序时可以更直接的 比较a+b与b+a的字典序
     */
    public String largestNumber(int[] nums) {
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(asStrs, (a, b) -> {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        });

        if (asStrs[0].equals("0")) {
            return "0";
        }

        StringBuilder largestNumberStr = new StringBuilder();
        for (String numAsStr : asStrs) {
            largestNumberStr.append(numAsStr);
        }

        return largestNumberStr.toString();
    }
}
