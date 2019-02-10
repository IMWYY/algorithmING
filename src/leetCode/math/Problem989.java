package leetCode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * For a non-negative integer X, the array-form of X is an array of its digits in left to right order.
 * For example, if X = 1231, then the array form is [1,2,3,1].
 * <p>
 * Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.
 * <p>
 * Example 1:
 * Input: A = [1,2,0,0], K = 34
 * Output: [1,2,3,4]
 * Explanation: 1200 + 34 = 1234
 * <p>
 * Example 2:
 * Input: A = [2,7,4], K = 181
 * Output: [4,5,5]
 * Explanation: 274 + 181 = 455
 * <p>
 * note:
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * If A.length > 1, then A[0] != 0
 * <p>
 * create by stephen on 2019/2/10
 */
public class Problem989 {

    public static void main(String[] args) {
        Problem989 p = new Problem989();
//        List<Integer> res = addToArrayForm(new int[]{}, 0);
//        List<Integer> res = addToArrayForm(new int[]{7}, 993);
//        List<Integer> res = addToArrayForm(new int[]{9,9,9,9,9,9,9,9,9,9}, 1);
        List<Integer> res = p.addToArrayForm1(new int[]{1, 2, 6, 3, 0, 7, 1, 7, 1, 9, 7, 5, 6, 6, 4, 4, 0, 0, 6, 3}, 516);
        System.out.println(res.size());
        for (int a : res) {
            System.out.print(a + " ");
        }
    }

    /**
     * 简洁的写法 直接将结果计算写入list
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ans = new ArrayList<>();
        int cur = K;
        int i = A.length;
        while (--i >= 0 || cur > 0) {
            if (i >= 0)
                cur += A[i];
            ans.add(0, cur % 10);
            cur /= 10;
        }
        return ans;
    }

    /**
     * 将所有的数字都放入数组 防止溢出
     * 需要考虑几个边界条件：
     * 1. 结果为0
     * 2. K的数字比A大,A遍历结束后仍然要继续计算K剩余的位数
     */
    public List<Integer> addToArrayForm1(int[] A, int K) {
        int[] B = new int[10001];
        Arrays.fill(B, 0);
        int bIndex = B.length - 1;
        while (K != 0) {
            B[bIndex--] = K % 10;
            K /= 10;
        }
        int carry = 0, aIndex = A.length - 1;
        bIndex = B.length - 1;
        while (aIndex >= 0 || bIndex >= 0) {
            int res = carry + B[bIndex];
            if (aIndex >= 0) {
                res += A[aIndex];
            }
            carry = res / 10;
            B[bIndex] = res % 10;
            aIndex--;
            bIndex--;
        }

        bIndex = 0;
        while (bIndex++ < B.length && B[bIndex] == 0);

        List<Integer> res = new ArrayList<>();
        if (bIndex == B.length) {
            res.add(0);
            return res;
        }

        while (bIndex < B.length) {
            res.add(B[bIndex++]);
        }
        return res;
    }
}
