package leetCode.dfsAndbfs;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest
 * transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * <p>
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * create by stephen on 2018/10/22
 */
@SuppressWarnings("all")
public class Problem127 {

    /**
     * 从头和尾都利用bfs算法
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

        int len = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<String>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordSet.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;
        }

        return 0;
    }

    /**
     * 标准BFS 每次添加忘队列添加的时候 不是遍历wordList 而是循环26个字母 减少时间复杂度
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0) return 0;
        Queue<String> queue = new ArrayDeque<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        addWord(beginWord, queue, wordSet);

        int curLevel = 1; // level 从1开始 所以这里是2

        while (!queue.isEmpty()) {
            int num = queue.size();
            for (int i = 0; i < num; ++i) {
                String cur = queue.poll();
                if (cur.equals(endWord)) return curLevel;
                addWord(cur, queue, wordSet);
            }
            curLevel++;
        }
        return 0;
    }

    private void addWord(String word, Queue<String> queue, Set<String> set) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char temp = chars[i];
            for (char j = 'a'; j <= 'z'; ++j) {
                chars[i] = j;
                String s = String.valueOf(chars);
                if (set.contains(s)) {
                    queue.add(s);
                    set.remove(s);  //这里需要在set中移除这个单词 否则会有重复添加
                }
            }
            chars[i] = temp;
        }
    }
}
