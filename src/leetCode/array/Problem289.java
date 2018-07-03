package leetCode.array;


/**
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 * using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 * <p>
 * Example:
 * <p>
 * Input:
 * [[0,1,0],
 * [0,0,1],
 * [1,1,1],
 * [0,0,0]]
 * Output:
 * [[0,0,0],
 * [1,0,1],
 * [0,1,1],
 * [0,1,0]]
 * create by stephen on 2018/7/3
 */
public class Problem289 {

    /**
     * 在原数组上修改 利用位运算记录当前和之前的状态
     * - 00  dead (next) <- dead (current)
     * - 01  dead (next) <- live (current)
     * - 10  live (next) <- dead (current)
     * - 11  live (next) <- live (current)
     * O(mn) time + O(mn) space
     */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, i, j);
                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    /**
     * 拷贝一份临时数据
     * O(mn) time + O(mn) space
     */
    public void gameOfLife1(int[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int[][] temp = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            System.arraycopy(board[i], 0, temp[i], 0, board[0].length);
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (i == 1 && j == 0) {
                    System.out.println("here");
                }
                int n = liveNeighbors(temp, i, j);
                if ((n < 2 && temp[i][j] == 1) || (n > 3 && temp[i][j] == 1)) {
                    board[i][j] = 0;
                } else if ((n == 3 && temp[i][j] == 0) || (n >= 2 && n <= 3 && temp[i][j] == 1)) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private int liveNeighbors(int[][] board, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, board.length - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, board[0].length - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
}
