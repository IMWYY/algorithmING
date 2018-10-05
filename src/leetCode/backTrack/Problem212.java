package leetCode.backTrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * <p>
 * Example:
 * Input:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * <p>
 * Output: ["eat","oath"]
 * create by stephen on 2018/10/5
 */
public class Problem212 {

    /**
     * 利用trie树+backtrack
     * 利用words构造一个特殊的trie树 然后扫描整个board并同时遍历trie
     * 叶节点保存word 遇到叶节点 保存结果到list
     * trie树同时省去了重复word的麻烦
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j, p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    private class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }


    /**
     * 一个个word判断是否在board中 借鉴Problem079的思路
     */
    public List<String> findWords1(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        if (board.length == 0 || board[0].length == 0) return new ArrayList<>();
        for (String s : words) {
            if (exist(board, s)) {
                res.add(s);
            }
        }
        return new ArrayList<>(res);
    }

    private boolean exist(char[][] board, String s) {
        if (s.length() == 0) return true;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (exist(board, s, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, String s, int index, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (index >= s.length()) return true;
        if (board[i][j] != s.charAt(index)) return false;
        board[i][j] -= 'z';
        boolean ret = exist(board, s, index + 1, i + 1, j) ||
                exist(board, s, index + 1, i, j + 1) ||
                exist(board, s, index + 1, i - 1, j) ||
                exist(board, s, index + 1, i, j - 1);
        board[i][j] += 'z';
        return ret;
    }
}

