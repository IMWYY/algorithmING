package leetCode.math;

/**
 * On a broken calculator that has a number showing on its display, we can perform two operations:
 * Double: Multiply the number on the display by 2, or;
 * Decrement: Subtract 1 from the number on the display.
 * Initially, the calculator is displaying the number X.
 * <p>
 * Return the minimum number of operations needed to display the number Y.
 * <p>
 * Example 1:
 * Input: X = 2, Y = 3
 * Output: 2
 * Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
 * <p>
 * Example 2:
 * Input: X = 5, Y = 8
 * Output: 2
 * Explanation: Use decrement and then double {5 -> 4 -> 8}.
 * <p>
 * note:
 * 1 <= X <= 10^9
 * 1 <= Y <= 10^9
 * <p>
 * create by stephen on 2019/2/10
 */
public class Problem991 {

    /**
     * X可以-1或者*2 <==> Y可以+1或者/2
     * 当Y比X小的时候，只能+1使Y变大，此时操作数为X-Y
     * 当Y比X大的时候，需要/2才能将Y变小
     * a. Y%2==1时，(Y+1)/2 两次操作，而(Y+3)/2 三次操作，所以首先+1
     * b. Y%2==0时，Y/2 + 1 两次操作，而 (Y+2)/2 三次操作，所以首先/2
     */
    public int brokenCalc(int X, int Y) {
        int ans = 0;
        while (Y > X) {
            ans++;
            if (Y % 2 == 1)
                Y++;
            else
                Y /= 2;
        }
        return ans + X - Y;
    }

    /**
     * 递归解法
     */
    public int brokenCalc1(int X, int Y) {
        if (X >= Y) return X - Y;
        return (Y & 1) == 0 ? 1 + brokenCalc(X, Y / 2) : 1 + brokenCalc(X, Y + 1);
    }
}
