package leetCode.dfsAndbfs;

import java.util.Arrays;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * <p>
 * Example 1:
 * Input: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * create by stephen on 2018/10/17
 */
public class Problem329 {

    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * 利用memorization 记录之前算的值 不用重复计算
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, memo);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }


    private int maxLen;
    /**
     * 最普通的DFS算法  : Time Limit Exceeded
     */
    public int longestIncreasingPath1(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (boolean[] booleans : visited) Arrays.fill(booleans, false);

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                dfs(matrix, visited, i, j, 1);
            }
        }
        return maxLen;
    }

    private void dfs(int[][] matrix, boolean[][] visited, int i, int j, int len) {
        maxLen = Math.max(len, maxLen);
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j] && !visited[i + 1][j]) {
            dfs(matrix, visited, i + 1, j, len + 1);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j] && !visited[i][j + 1]) {
            dfs(matrix, visited, i, j + 1, len + 1);
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j] && !visited[i - 1][j]) {
            dfs(matrix, visited, i - 1, j, len + 1);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j] && !visited[i][j - 1]) {
            dfs(matrix, visited, i, j - 1, len + 1);
        }
        visited[i][j] = false;
    }

}
