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
		Point[] points = new Point[] { new Point(0, 0), new Point(94911151, 94911150), new Point(94911152, 94911151) };
		System.out.println(new Problem149().maxPoints(points));
	}

	/**
	 * 利用分数表示斜率 回避了精度问题 但是需要计算最大公约数
	 */
	public int maxPoints(Point[] points) {
		if (points == null)
			return 0;
		if (points.length <= 2)
			return points.length;

		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
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

				if (map.containsKey(x)) {
					if (map.get(x).containsKey(y)) {
						map.get(x).put(y, map.get(x).get(y) + 1);
					} else {
						map.get(x).put(y, 1);
					}
				} else {
					Map<Integer, Integer> m = new HashMap<>();
					m.put(y, 1);
					map.put(x, m);
				}
				max = Math.max(max, map.get(x).get(y));
			}
			result = Math.max(result, max + overlap + 1);
		}
		return result;

	}

	private int generateGCD(int a, int b) {
		if (b == 0) return a;
		else return generateGCD(b, a % b);
	}

	/**
	 * 需要考虑重叠的点 且重叠的点初始化为1
	 * 这个方案[[0,0],[94911151,94911150],[94911152,94911151]] 会有精度问题
	 */
	public int maxPoints2(Point[] points) {
		if (points == null)
			return 0;
		if (points.length <= 2)
			return points.length;

		int res = 0;
		Map<Float, Integer> map = new HashMap<>();
		for (int i = 0; i < points.length; ++i) {
			map.clear();
			int overLap = 1, loaclMax = 0;
			for (int j = i + 1; j < points.length; ++j) {
				if (points[i].x == points[j].x && points[i].y == points[j].y) {
					overLap++;
				} else if (points[i].x == points[j].x) {
					map.put(Float.MAX_VALUE, map.getOrDefault(Float.MAX_VALUE, 0) + 1);
					loaclMax = Math.max(loaclMax, map.get(Float.MAX_VALUE));
				} else if (points[i].y == points[j].y) {
					map.put((float) 0, map.getOrDefault((float) 0, 0) + 1);
					loaclMax = Math.max(loaclMax, map.get((float) 0));
				} else {
					float key = ((float) (points[i].y - points[j].y)) / (points[i].x - points[j].x);
					map.put(key, map.getOrDefault(key, 0) + 1);
					loaclMax = Math.max(loaclMax, map.get(key));
				}

				res = Math.max(res, loaclMax + overLap);

			}
		}

		return res;
	}

	private static class Point {
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
