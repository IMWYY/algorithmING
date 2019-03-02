package leetCode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * create by stephen on 2018/9/11
 */
public class Problem149 {

    public static void main(String[] args) {
        Point[] points = new Point[]{new Point(0, 0), new Point(94911151, 94911150), new Point(94911152, 94911151)};
        System.out.println(new Problem149().maxPoints(points));
    }

    /**
     * 需要注意的细节：
     * 1. 重复的点 需要特别计算
     * 2. 分数会有精度问题 这里利用分数表示斜率 但是需要化简分数 所以用到了辗转相除法求最大公约数
     */
    public int maxPoints(Point[] points) {
        if (points == null)
            return 0;
        if (points.length <= 2)
            return points.length;

        Map<String, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int overlap = 0, max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                int gcd = generateGCD(x, y);
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }

                String key = y + "/" + x;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            result = Math.max(result, max + overlap + 1);
        }
        return result;
    }

    /**
     * 辗转相除法求最大公约数
     * a,b的最大公约数 = b,a%b的最大公约数相同
     */
    private int generateGCD(int a, int b) {
        if (b == 0) return a;
        else return generateGCD(b, a % b);
    }

    private static class Point {
        int x;
        int y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}
