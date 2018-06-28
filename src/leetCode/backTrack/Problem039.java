package leetCode.backTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * create by stephen on 2018/6/28
 */
public class Problem039 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, candidates, target, 0, new ArrayList<>());
        return result;
    }

    private void backTrack(List<List<Integer>> result, int[] candidates, int target, int start, ArrayList<Integer> tempList) {
        if (target < 0) return;
        if (target == 0) result.add(new ArrayList<>(tempList));

        for (int i=start; i<candidates.length; ++i) {
            tempList.add(candidates[i]);
            backTrack(result, candidates, target-candidates[i], i, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}
