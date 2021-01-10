package leetCode.dfsAndbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
 * both indicate a queen and an empty space respectively.
 * create by stephen on 2019/3/1
 */
public class Problem051 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        dfs(board, 0, res, new HashMap<>());
        return res;
    }

    private void dfs(char[][] grid, int r, List<List<String>> res, HashMap<String, Integer> cache) {
        if (r == grid.length) {
            List<String> list = new ArrayList<>();
            for (char[] g : grid) {
                list.add(String.valueOf(g));
            }
            res.add(list);
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
            dfs(grid, r + 1, res, cache);
            cache.put("c:" + c, 0);
            cache.put("d1:" + (r + c), 0);
            cache.put("d2:" + (r - c), 0);
            grid[r][c] = '.';
        }
    }

}
