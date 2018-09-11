package leetCode.hash;

import java.util.HashMap;
import java.util.HashSet;
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
        Point[] points = new Point[]{new Point(1, 1), new Point(3, 2), new Point(5, 3),
                new Point(4, 1), new Point(2, 3), new Point(1, 4)};
        System.out.println(maxPoints(points));
    }

    /**
     * 需要考虑重叠的点 且重叠的点初始化为1
     */
    public static int maxPoints(Point[] points) {
        if (points==null) return 0;
        if (points.length<=2) return points.length;

        int res = 0;
        Map<Float, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; ++i) {
            map.clear();
            int overLap = 1, loaclMax = 0;
            for (int j = i+1; j < points.length; ++j) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    overLap ++;
                } else if (points[i].x == points[j].x) {
                    map.put(Float.MAX_VALUE, map.getOrDefault(Float.MAX_VALUE, 0) + 1);
                    loaclMax = Math.max(loaclMax, map.get(Float.MAX_VALUE));
                } else {
                    if (points[i].y == points[j].y) {
                        map.put((float)0, map.getOrDefault((float)0, 0) + 1);
                        loaclMax = Math.max(loaclMax, map.get((float)0));
                    } else {
                        float key = ((float) (points[i].y - points[j].y)) / (points[i].x - points[j].x);
                        map.put(key, map.getOrDefault(key, 0) + 1);
                        loaclMax = Math.max(loaclMax, map.get(key));
                    }
                }

                res = Math.max(res,loaclMax + overLap);

            }
        }

        return res;
    }

    public static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}
