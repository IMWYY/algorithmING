package leetCode.hash;

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
            if (cache.getOrDefault("1:" + r, 0) > 0 || cache.getOrDefault("2:" + c, 0) > 0
                    || cache.getOrDefault("3:" + (r + c), 0) > 0 || cache.getOrDefault("4:" + (r - c), 0) > 0) {
                continue;
            }
            cache.put("1:" + r, cache.getOrDefault("1:" + r, 0) + 1);
            cache.put("2:" + c, cache.getOrDefault("2:" + c, 0) + 1);
            cache.put("3:" + (r + c), cache.getOrDefault("3:" + (r + c), 0) + 1);
            cache.put("4:" + (r - c), cache.getOrDefault("4:" + (r - c), 0) + 1);
            grid[r][c] = 'Q';
            dfs(grid, r + 1, res, cache);
            cache.put("1:" + r, cache.getOrDefault("1:" + r, 0) - 1);
            cache.put("2:" + c, cache.getOrDefault("2:" + c, 0) - 1);
            cache.put("3:" + (r + c), cache.getOrDefault("3:" + (r + c), 0) - 1);
            cache.put("4:" + (r - c), cache.getOrDefault("4:" + (r - c), 0) - 1);
            grid[r][c] = '.';
        }
    }

}
