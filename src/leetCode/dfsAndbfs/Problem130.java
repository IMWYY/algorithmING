package leetCode.dfsAndbfs;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * Example:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * create by stephen on 2018/10/21
 */
public class Problem130 {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'O', 'X', 'O', 'O', 'O', 'X'}, {'O', 'O', 'X', 'X', 'X', 'O'}, {'X', 'X', 'X', 'X', 'X', 'O'},
                {'O', 'O', 'O', 'O', 'X', 'X'}, {'X', 'X', 'O', 'O', 'X', 'O'}, {'O', 'O', 'X', 'X', 'X', 'X'}};
        new Problem130().solve(board);
        for (char[] aBoard : board) {
            for (int j = 0; j < board[0].length; ++j) {
                System.out.print(aBoard[j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 反向思维 不去找所有被包围的O 而是将不满足条件的O全部去除 然后将剩余的（满足条件的）flip
     * a. 从board的边开始dfs 将所有边可以到达的O标记为1,
     * b. 将board的所有O变成X
     * c. 将board的所有1变成O
     * O(n^2) time + O(1) space
     */
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int row = board.length, col = board[0].length;

        for (int i = 0; i < row; ++i) {
            dfs(board, i, 0);
            dfs(board, i, col - 1);
        }
        for (int j = 0; j < col; ++j) {
            dfs(board, 0, j);
            dfs(board, row - 1, j);
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == '1') board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != 'O') return;
        if (board[i][j] == 'O') board[i][j] = '1';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

}
