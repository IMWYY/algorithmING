package leetCode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * <p>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * <p>
 */
public class Problem036 {

    public boolean isValidSudoku(char[][] board) {
        Set<Character> rowSet = new HashSet<>();
        Set<Character> colSet = new HashSet<>();
        Set<Character> boxSet = new HashSet<>();
        for (int i = 0; i < 9; ++i) {
            rowSet.clear();
            colSet.clear();
            boxSet.clear();
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.' && !rowSet.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != '.' && !colSet.add(board[j][i])) {
                    return false;
                }
                if (board[(i / 3) * 3 + j / 3][(i % 3) * 3 + j % 3] != '.'
                        && !boxSet.add(board[(i / 3) * 3 + j / 3][(i % 3) * 3 + j % 3])) {
                    return false;
                }
            }
        }
        return true;
    }
}
