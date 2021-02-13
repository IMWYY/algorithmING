package leetCode.backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 */
public class Problem047 {

	public static void main(String[] args) {
		Problem047 p = new Problem047();
		System.out.println(p.permuteUnique(new int[]{2, 2, 2}).size());
	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
		return list;
	}

	/**
	 * 数组中有重复元素，在添加元素的时候需要去重
	 * 因此需要一个额外数字记录当前数字是否被处理过
	 */
	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
		if (tempList.size() == nums.length) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				// 如果这个元素被使用过 或者 和前一个元素相同且前一个元素没有被添加过
				// 这里第二个条件也可以写成 i > 0 && nums[i] == nums[i - 1] && used[i - 1]
				// 但是会多一些无用的遍历 增加时间复杂度
				if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
					continue;
				used[i] = true;
				tempList.add(nums[i]);
				backtrack(list, tempList, nums, used);
				used[i] = false;
				tempList.remove(tempList.size() - 1);
			}
		}
	}
}
