package leetCode.string;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 */
public class Problem017 {
    // iterative solution
    public List<String> letterCombinations(String digits) {
        LinkedList<String> result = new LinkedList<>();
        if (digits == null || digits.length() == 0) return result;
        result.add("");
        String[] letters = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (int i = 0; i < digits.length(); ++i) {
            String letter = letters[digits.charAt(i) - '0'];
            while (result.peek().length() == i) {
                String removed = result.remove();
                for (char c : letter.toCharArray()) {
                    result.add(removed + c);
                }
            }
        }
        return result;
    }
}
