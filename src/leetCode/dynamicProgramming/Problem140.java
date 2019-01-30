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


    /**
     * dp算法变形 每次都记录当前dict下标位置的所有string组合
     * 对于这种testcase会Memory Limit Exceeded
     * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
     * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
     * 解决方法：
     * 1.可以利用wordbreak (problem139) 先判断是否是breakable
     * 2.先将string加入 先加入index 利用index判断是否breakable
     */
    public List<String> wordBreak1(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (!isBreakable(s, wordDict)) return res;
        List<List<String>> dp = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            List<String> list = new ArrayList<>();
            dp.add(list);

            for (String word : wordDict) {
                if (i + 1 == word.length() && s.substring(0, i + 1).equals(word)) {
                    dp.get(i).add(word + " ");
                } else if (i + 1 > word.length() && s.substring(i + 1 - word.length(), i + 1).equals(word)
                        && dp.get(i - word.length()).size() > 0) {
                    for (String sb : dp.get(i - word.length())) {  // 将之前的加入
                        dp.get(i).add(sb + word + " ");
                    }
                }
            }
        }

        for (String sb : dp.get(dp.size() - 1)) {
            res.add(sb.substring(0, sb.length() - 1));
        }
        return res;
    }

    @SuppressWarnings("all")
    public boolean isBreakable(String s, List<String> wordDict) {
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
        return f[s.length()];
    }


    @SuppressWarnings("all")
    public List<String> wordBreak2(String s, List<String> wordDict) {
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
//        if (!f[s.length()]) {
//            return new ArrayList<>();
//        }

        List<String> res = new ArrayList<>();
        findWord(f, s.length()-1, new ArrayList<>(), res, set);
        return res;
    }

    private void findWord(boolean[] f, int i, ArrayList<String> tmpList, List<String> res, Set<String> wordDict) {
        if (!f[i+1]) {
            return;
        }

    }

}
