//
// Created by 王友运 on 2019-03-01.
//

#include <vector>
#include <string>
#include <unordered_map>

using namespace std;


/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 */


void dfs(char **board, int n, vector<vector<string>> res, int row, unordered_map<string, int> cache) {
    if (row == n) {
    }
    return;
}

vector<vector<string>> solveNQueens(int n) {
    vector<vector<string>> res;
    unordered_map<string, int> cache;
    char **board = new char *[10];
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            board[i][j] = '.';
        }
    }
    dfs(board, n, res, 0, cache);
    return res;
}

