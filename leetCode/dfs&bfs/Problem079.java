package leetCode.dfsAndbfs;

import java.util.Arrays;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 */
public class Problem079 {

    public static boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        if (word.length() == 0) return true;

        boolean[][] used = new boolean[board.length][board[0].length];
        for (boolean[] anUsed : used) {
            Arrays.fill(anUsed, false);
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (findWord(board, word, used, 1, i, j))
                    return true;
            }
        }
        return false;
    }

    private static boolean findWord(char[][] board, String word, boolean[][] used, int index, int i, int j) {
        if (index >= word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (board[i][j] != word.charAt(index)) return false;

        used[i][j] = true;
        boolean exist = findWord(board, word, used, index + 1, i + 1, j)
                || findWord(board, word, used, index + 1, i - 1, j)
                || findWord(board, word, used, index + 1, i, j + 1)
                || findWord(board, word, used, index + 1, i, j - 1);
        used[i][j] = false;
        return exist;
    }
}
