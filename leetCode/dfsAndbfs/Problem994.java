package leetCode.dfsAndbfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1 instead.
 * <p>
 * Example 1:
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * <p>
 * Example 2:
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten,
 * because rotting only happens 4-directionally.
 * <p>
 * Example 3:
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 * <p>
 * Note:
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] is only 0, 1, or 2.
 * create by stephen on 2019/2/17
 */
public class Problem994 {

    public static void main(String[] args) {
        Problem994 p = new Problem994();
        System.out.println(p.orangesRotting(new int[][]{
                {2, 1, 1}, {1, 1, 0}, {0, 1, 1},
        }));
        System.out.println(p.orangesRotting(new int[][]{
                {2, 1, 1}, {0, 1, 1}, {1, 0, 1},
        }));
        System.out.println(p.orangesRotting(new int[][]{
                {0, 2}
        }));
    }

    /**
     * BFS 同时在每一轮遍历时为了不干扰结果 让新rotten的cell的数字递增
     * 用freshCount记录当前的fresh数量，如果结束时fresh大于0，返回-1
     */
    public int orangesRotting(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int count, res = 0, newRotten = 2, freshCount;
        while (true) {
            count = freshCount = 0;
            newRotten++;
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (grid[i][j] == 1) freshCount++;
                    if (grid[i][j] < 2 || grid[i][j] == newRotten) continue;
                    if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                        grid[i + 1][j] = newRotten;
                        count++;
                    }
                    if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                        grid[i][j + 1] = newRotten;
                        count++;
                    }
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        grid[i - 1][j] = newRotten;
                        count++;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        grid[i][j - 1] = newRotten;
                        count++;
                    }
                }
            }
            if (count == 0) {
                if (freshCount > 0) {
                    return -1;
                }
                return res;
            }
            res++;
        }
    }

    public int orangesRotting1(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        int[] dr = new int[]{-1, 0, 1, 0};
        int[] dc = new int[]{0, -1, 0, 1};

        // queue : all starting cells with rotten oranges
        Queue<Integer> queue = new ArrayDeque<>();
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c)
                if (grid[r][c] == 2) {
                    int code = r * C + c;
                    queue.add(code);
                }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            boolean rotten = false;
            for (int i = 0; i < n; ++i) {
                int code = queue.remove();
                int r = code / C, c = code % C;
                for (int k = 0; k < 4; ++k) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        queue.add(nr * C + nc);
                        rotten = true;
                    }
                }
            }
            if (rotten) ans++;
        }

        for (int[] row : grid)
            for (int v : row)
                if (v == 1) return -1;
        return ans;

    }
}
