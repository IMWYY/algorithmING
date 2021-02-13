package leetCode.backTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 */
public class Problem046 {

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), nums);
		return list;
	}

	/**
	 *  注意这个数组中的数字都不重复
	 *  所以只需要判断数字的值是否重复就可以判断排列是否重复
	 */
	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
		if (tempList.size() == nums.length) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int num : nums) {
				if (tempList.contains(num)) continue; // element already exists, skip
				tempList.add(num);
				backtrack(list, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
}
