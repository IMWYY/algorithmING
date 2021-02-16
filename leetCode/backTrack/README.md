# Back-Track

a solution template，refer to [here](https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning))

```c++
std::vector<std::vector<int>>& solve(std::vector<int>& nums) {
    std::vector<std::vector<int>> res;
   	// preform some initialization such as sort array
    initialization();
    std::vector<int> tmplist;
    std::vector<bool> used(nums.size(), false);
    backtrack(res, tmplist, nums, used, 0);
    return res;
}

void backtrack(std::vector<std::vector<int>>& list , std::vector<int>& tmplist, std::vector<int>& nums, std::vector<bool>& used, int start){
    if (someCondition) {						// add the tmplist to result according to some condition，such as length
    	list.add(new ArrayList<>(tempList));
    }
    for(int i = start; i < nums.length; i++){
        if (used[i]) continue;
        /**
         * if (used[i] || i > 0 && nums[i] = nums[i-1] && !used[i-1]) continue;
         * sometimes we need to de-duplicate, see Problem047
         * (nums[i] = nums[i-1] && !used[i-1]) is more efficient than (nums[i] = nums[i-1] && used[i-1])
         */
        tempList.add(nums[i]);         // add current element to list
        backtrack(list, tempList, nums, used, i + 1);		// next start can i or i+1
        tempList.remove(tempList.size() - 1);		// remove the element from list
    }
}
```
