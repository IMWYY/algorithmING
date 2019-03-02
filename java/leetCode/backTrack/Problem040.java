package leetCode.backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 */
public class Problem040 {

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);  // 排序数组方便去重
		backTrack(result, candidates, target, 0, new ArrayList<>());
		return result;
	}

	/**
	 * 回溯法 注意这里元素不可以重复，递归时的index需要+1 同时需要在条件判断里去除重复
	 */
	private void backTrack(List<List<Integer>> result, int[] candidates, int target, int start, ArrayList<Integer> tempList) {
		if (target < 0) return;
		if (target == 0) result.add(new ArrayList<>(tempList));

		for (int i=start; i<candidates.length; ++i) {
			if (i > start && candidates[i] == candidates[i-1]) continue;
			tempList.add(candidates[i]);
			backTrack(result, candidates, target-candidates[i], i+1, tempList);
			tempList.remove(tempList.size() - 1);
		}
	}

}
