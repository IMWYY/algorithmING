package leetCode;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 * <p>
 * create by stephen on 2018/5/3
 */
public class Problem011 {

    /**
     * 前后两个指针法 每次小height的指针往中间移动
     */
    public int maxArea(int[] height) {
        int res = 0, start = 0, end = height.length - 1;
        while (start < end) {
            res = Math.max(res, (end - start) * Math.min(height[end], height[start]));
            if (height[end] > height[start]) start++;
            else end--;
        }
        return res;
    }


    /**
     * 暴力解法  Time Limit Exceeded
     */
    public int maxArea1(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; ++i) {
            for (int j = i + 1; j < height.length; ++j) {
                res = Math.max(res, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return res;
    }


}
