package leetCode.dfsAndbfs;

import java.util.Arrays;
import java.util.HashMap;

/**
 * create by stephen on 2019/3/1
 */
public class Problem052 {
    int res = 0;

    /**
     * 这里hashmap可以优化为数组 从而使运行时间更短
     */
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        dfs(board, 0, new HashMap<>());
        return res;
    }

    private void dfs(char[][] grid, int r, HashMap<String, Integer> cache) {
        if (r == grid.length) {
            res++;
            return;
        }
        for (int c = 0; c < grid[r].length; ++c) {
            if (cache.getOrDefault("c:" + c, 0) > 0
                    || cache.getOrDefault("d1:" + (r + c), 0) > 0
                    || cache.getOrDefault("d2:" + (r - c), 0) > 0) {
                continue;
            }
            cache.put("c:" + c, 1);
            cache.put("d1:" + (r + c), 1);
            cache.put("d2:" + (r - c), 1);
            grid[r][c] = 'Q';
            dfs(grid, r + 1, cache);
            cache.put("2:" + c, 0);
            cache.put("d1:" + (r + c), 0);
            cache.put("d2:" + (r - c), 0);
            grid[r][c] = '.';
        }
    }

}
