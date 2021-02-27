package leetCode.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem1001 {

    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        int[] res = new int[queries.length];
        if (lamps.length == 0 || queries.length == 0) return res;

        Arrays.fill(res, 0);

        Map<Integer, Integer> cache = new HashMap<>();
        Map<Integer, Integer> lampState = new HashMap<>();
        for (int[] ints : lamps) {
            lampState.put(ints[0] * N + ints[1], 1);
        }
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};

        for (int i = 0; i < queries.length; ++i) {
            int x = queries[i][0], y = queries[i][1];
            if (cache.getOrDefault(x * N + y, 0) == 1) {
                res[i] = 1;
                continue;
            }
            for (int[] l : lamps) {
                if (lampState.getOrDefault(l[0] * N + l[1], 0) != 1) {
                    continue;
                }
                int xDiff = Math.abs(x - l[0]), yDiff = Math.abs(y - l[1]);
                if (xDiff == 0 || yDiff == 0 || xDiff == yDiff) {
                    res[i] = 1;
                    cache.put(x * N + y, 1);
                    for (int[] dir : dirs) {
                        if (x + dir[0] >= 0 && x + dir[0] < N && y + dir[1] >= 0 && y + dir[1] < N
                                && lampState.containsKey((x + dir[0]) * N + y + dir[1])) {
                            lampState.put((x + dir[0]) * N + y + dir[1], -1);
                        }
                    }
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 利用直角坐标系 记录每个点被照亮的次数
     * 对角的为x+y 和 x-y
     */
    public int[] gridIllumination1(int N, int[][] lamps, int[][] queries) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {0, 0}};
        Map<Integer, Integer> m1 = new HashMap<>();       // row number to count of lamps
        Map<Integer, Integer> m2 = new HashMap<>();       // col number to count of lamps
        Map<Integer, Integer> m3 = new HashMap<>();       // diagonal x-y to count of lamps
        Map<Integer, Integer> m4 = new HashMap<>();       // diagonal x+y to count of lamps
        Map<Integer, Boolean> m5 = new HashMap<>();       // whether lamp is  ON|OFF

        // increment counters while adding lamps
        for (int[] lamp : lamps) {
            int x = lamp[0];
            int y = lamp[1];
            m1.put(x, m1.getOrDefault(x, 0) + 1);
            m2.put(y, m2.getOrDefault(y, 0) + 1);
            m3.put(x - y, m3.getOrDefault(x - y, 0) + 1);
            m4.put(x + y, m4.getOrDefault(x + y, 0) + 1);
            m5.put(N * x + y, true);
        }

        int[] ans = new int[queries.length];
        // address queries
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];

            ans[i] = (m1.getOrDefault(x, 0) > 0 || m2.getOrDefault(y, 0) > 0
                    || m3.getOrDefault(x - y, 0) > 0 || m4.getOrDefault(x + y, 0) > 0)
                    ? 1 : 0;
            // switch off the lamps, if any
            for (int[] dir : dirs) {
                int x1 = x + dir[0];
                int y1 = y + dir[1];
                if ((x >= 0 && x < N && y >= 0 && y < N) && m5.getOrDefault(N * x1 + y1, false)) {
                    // the lamp is on, turn it off, so decrement the count of the lamps
                    m1.put(x1, m1.getOrDefault(x1, 1) - 1);
                    m2.put(y1, m2.getOrDefault(y1, 1) - 1);
                    m3.put(x1 - y1, m3.getOrDefault(x1 - y1, 1) - 1);
                    m4.put(x1 + y1, m4.getOrDefault(x1 + y1, 1) - 1);
                    m5.put(N * x1 + y1, false);
                }
            }
        }
        return ans;
    }
}