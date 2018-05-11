package leetCode;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * <p>
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 * create by stephen on 2018/5/11
 */
public class Problem004 {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2, 3, 4, 5, 6}, new int[]{}));
    }

    /**
     * 改进的二分查找。
     * 找到两个数组的下标i，j，使得被分割的两部分的数量相等（或者相差一），且左半部分最大值小于右半部分最小值
     * 注意：
     * 1. i对应的下标为长度小的数组，j对应的下标为长度长的数组
     * 2. 边界情况的处理
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) return 0;
        int m = nums1.length, n = nums2.length;

        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            m = nums1.length;
            n = nums2.length;
        }

        if (m == 0) {
            if ((n & 1) == 0) {
                return (nums2[(n >> 1) -1] + nums2[n >> 1]) / 2.0;
            } else {
                return nums2[n >> 1];
            }
        }

        int left = 0, right = m, i, j;
        while (left <= right) {
            i = (left + right) / 2;
            j = (m + n + 1) / 2 - i;

            // 注意边界情况
            if (i > 0 && j < n &&  nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else if (j > 0 && i < m &&  nums2[j - 1] > nums1[i]) {
                left = i + 1;
            } else if ((i <= 0 || j >= n  || nums1[i - 1] <= nums2[j]) &&
                    (j <= 0 || i >= m || nums2[j - 1] <= nums1[i])) {
                int maxLeft;
                if (i <= 0) maxLeft = nums2[j-1];
                else if (j <= 0) maxLeft = nums1[i-1];
                else maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);

                if (((m + n) & 1) == 1) {
                    return maxLeft;
                }

                int minRight;
                if (i >= m) minRight = nums2[j];
                else if (j >= n) minRight = nums1[i];
                else minRight = Math.min(nums1[i], nums2[j]);

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0;
    }
}
