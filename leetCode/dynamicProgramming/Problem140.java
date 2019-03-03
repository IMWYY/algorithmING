package leetCode.dynamicProgramming;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * <p>
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * <p>
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * create by stephen on 2018/10/4
 */
public class Problem140 {

    /**
     * DFS算法 同时利用一个hashmap保存之前的值
     * key: 要分割的string s; value: 分割成功的字符串
     * memorization
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s)) return map.get(s);

        LinkedList<String> res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);
        return res;
    }


    //"pineapplepenapple"
    //["apple","pen","applepen","pine","pineapple"]
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("pen");
        words.add("applepen");
        words.add("pine");
        words.add("pineapple");
        List<String> res = wordBreak2("pineapplepenapple", words);

        for (String s : res) {
            System.out.println(s);
        }
    }


    @SuppressWarnings("all")
    /**
     * dp回溯 根据Problem139的接过倒推得到结果
     * 不过需要注意在删除元素的时候 需要删除最后一次出现的元素 否则在有重复元素出现的时候会有顺序问题
     * 见例子  pineapplepenapple ["apple","pen","applepen","pine","pineapple"]
     */
    public static List<String> wordBreak2(String s, List<String> wordDict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && set.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }

        List<String> res = new ArrayList<>();
        findWord(s, f, s.length(), new ArrayList<>(), res, set);
        return res;
    }

    private static void findWord(String s, boolean[] f, int index, ArrayList<String> tmpList, List<String> res, Set<String> wordDict) {
        if (index == 0) {
            StringBuilder sb = new StringBuilder();
            for (int j = tmpList.size() - 1; j >= 0; j--) {
                if (j == tmpList.size() - 1) {
                    sb.append(tmpList.get(j));
                } else {
                    sb.append(" ").append(tmpList.get(j));
                }
            }
            res.add(sb.toString());
            return;
        }
        if (!f[index]) return;
        for (int j = index - 1; j >= 0; j--) {
            if (f[j] && wordDict.contains(s.substring(j, index))) {
                tmpList.add(s.substring(j, index));
                findWord(s, f, j, tmpList, res, wordDict);
                for (int k = tmpList.size() - 1; k >= 0; k--) {
                    if (tmpList.get(k).equals(s.substring(j, index))) {
                        tmpList.remove(k);
                        break;
                    }
                }
                //这里删除需要删除最后一次出现而不是第一次出现的的元素 不然当有相同元素时会有乱序的问题
            }
        }
    }

}
