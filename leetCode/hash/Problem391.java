package leetCode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * Given N axis-aligned rectangles where N > 0, determine if they all together form
 * an exact cover of a rectangular region.
 * Each rectangle is represented as a bottom-left point and a top-right point.
 * For example, a unit square is represented as [1,1,2,2].
 * (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 */
public class Problem391 {

    public static void main(String[] args) {
        Problem391 p = new Problem391();
        System.out.println(p.isRectangleCover(new int[][]{
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {3, 2, 4, 4},
                {1, 3, 2, 4},
                {2, 3, 3, 4}
        }));
        System.out.println(p.isRectangleCover(new int[][]{
                {1, 1, 2, 3},
                {1, 3, 2, 4},
                {3, 1, 4, 2},
                {3, 2, 4, 4}
        }));
        System.out.println(p.isRectangleCover(new int[][]{
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {1, 3, 2, 4},
                {3, 2, 4, 4}
        }));
        System.out.println(p.isRectangleCover(new int[][]{
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {1, 3, 2, 4},
                {2, 2, 4, 4}
        }));
    }

    // todo sweep line algorithm

    /**
     * 找到最大长方形的范围
     * 如果是刚好覆盖一个长方形需要满足以下两个条件
     * 1. 面积和与最大的长方形相等
     * 2. 除了最大长方形的四个顶点 所有的点都会出现两次
     * O(n) time + O(n) space
     */
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 1) return true;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;
        int area = 0;
        Set<String> set = new HashSet<>();
        for (int[] rect : rectangles) {
            minX = Math.min(minX, rect[0]);
            minY = Math.min(minY, rect[1]);
            maxX = Math.max(maxX, rect[2]);
            maxY = Math.max(maxY, rect[3]);
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);

            String s1 = rect[0] + " " + rect[1];
            String s2 = rect[0] + " " + rect[3];
            String s3 = rect[2] + " " + rect[3];
            String s4 = rect[2] + " " + rect[1];

            if (!set.add(s1)) set.remove(s1);
            if (!set.add(s2)) set.remove(s2);
            if (!set.add(s3)) set.remove(s3);
            if (!set.add(s4)) set.remove(s4);
        }
        return area == (maxX - minX) * (maxY - minY)
                && set.size() == 4
                && set.contains(minX + " " + minY)
                && set.contains(minX + " " + maxY)
                && set.contains(maxX + " " + minY)
                && set.contains(maxX + " " + maxY);
    }

    /**
     * 找到最大长方形的范围 检查范围内的每个unit有且只有一个rect覆盖它
     * 当边长很长时会time exceed limit
     */
    public boolean isRectangleCover1(int[][] rectangles) {
        if (rectangles.length == 1) return true;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;
        int area = 0;
        for (int[] rect : rectangles) {
            minX = Math.min(minX, rect[0]);
            minY = Math.min(minY, rect[1]);
            maxX = Math.max(maxX, rect[2]);
            maxY = Math.max(maxY, rect[3]);
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
        }
        if (area != (maxX - minX) * (maxY - minY)) return false;

        int count;
        for (int i = minX; i < maxX; ++i) {
            for (int j = minY; j < maxY; ++j) {
                count = 0;
                for (int[] rect : rectangles) {
                    if (rect[0] <= i && rect[1] <= j && rect[2] > i && rect[3] > j) {
                        count++;
                        if (count > 1) return false;
                    }
                }
                if (count == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
