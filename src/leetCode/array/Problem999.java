package leetCode.array;

/**
 * On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.
 * These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces,
 * and lowercase characters represent black pieces.
 * <p>
 * The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south),
 * then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite
 * colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.
 * <p>
 * Return the number of pawns the rook can capture in one move.
 * <p>
 * create by stephen on 2019/2/27
 */
public class Problem999 {

    public int numRookCaptures(char[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 'R') {
                    int res = 0;
                    for (int[] dir : new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                        for (int ii = i, jj = j; ii >= 0 && ii < board.length && jj >= 0 && jj < board[0].length; ii += dir[0], jj += dir[1]) {
                            if (board[ii][jj] == 'B') break;
                            if (board[ii][jj] == 'p') {
                                res++;
                                break;
                            }
                        }
                    }
                    return res;
                }
            }
        }
        return 0;
    }
}
