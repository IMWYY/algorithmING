package leetCode.twoPoint;

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
     * 两指针法
     * start和end记录两个边界
     * 因为容量大小由短的边决定 每次小height的指针往中间移动 同时记录每一步都更新maxArea
     * O(n) time + O(1) space
     */
    public int maxArea(int[] height) {
        int res = 0, start = 0, end = height.length - 1;
        while (start < end) {
            res = Math.max(res, (end - start) * Math.min(height[end], height[start]));
            if (height[end] > height[start]){
                start++;
            } else {
                end--;
            }
        }
        return res;
    }

}
