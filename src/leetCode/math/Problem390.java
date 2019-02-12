package leetCode.math;

/**
 * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and
 * every other number afterward until you reach the end of the list.
 * Repeat the previous step again, but this time from right to left, remove the right most number
 * and every other number from the remaining numbers.
 * We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 * Find the last number that remains starting with a list of length n.
 * <p>
 * Example:
 * Input:
 * n = 9,
 * 1 2 3 4 5 6 7 8 9
 * 2 4 6 8
 * 2 6
 * 6
 * Output:6
 * create by stephen on 2019/2/12
 */
public class Problem390 {
    /**
     * 奇数和前一个偶数结果相同
     * 偶数相当于/2之后的序列 相当于原题目要求的镜像 先right后left
     */
    public int lastRemaining(int n) {
        if (n == 1) return 1;
        if (n == 2 || n == 3) return 2;
        if (n % 2 == 1) {
            return lastRemaining(n - 1);
        }
        return 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }

    /**
     * 保存一个head位置 最后当剩余数字只有一个的时候 head就是结果
     * 每一轮remove step都会*2
     * 而head并不是每一轮都会往前移动
     * a. 如果从左往后 head+= step
     * b. 如果从右往左 需要看这一轮的remove会不会涉及到head
     */
    public int lastRemaining1(int n) {
        boolean left = true;
        int remaining = n;
        int step = 1;
        int head = 1;
        while (remaining > 1) {
            if (left || remaining % 2 == 1) {
                head = head + step;
            }
            remaining = remaining / 2;
            step = step * 2;
            left = !left;
        }
        return head;
    }
}
