# Back-Track

A solution template，refer to [here](https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning))

```c++
std::vector<std::vector<int>>& solve(std::vector<int>& nums) {
    std::vector<std::vector<int>> res;
    initialization();   // preform some initialization such as sortin array
    std::vector<int> tmplist;
    std::vector<bool> used(nums.size(), false);
    backtrack(res, tmplist, nums, used, 0);
    return res;
}

void backtrack(std::vector<std::vector<int>>& finalres , std::vector<int>& tmplist, std::vector<int>& nums, std::vector<bool>& used, int start){
    if (someCondition) {    // add the tmplist to result according to some condition，such as length
    	finalres.add(new ArrayList<>(tempList));
    }
    for(int i = start; i < nums.length; i++){
        if (used[i]) continue;
        /**
         * if (used[i] || i > 0 && nums[i] = nums[i-1] && !used[i-1]) continue;
         * sometimes we need to de-duplicate, see Problem047
         * (nums[i] = nums[i-1] && !used[i-1]) is more efficient than (nums[i] = nums[i-1] && used[i-1])
         */
        used[i] = true;
        tempList.add(nums[i]);         // add current element to list
        backtrack(finalres, tempList, nums, used, i + 1);		// next start can i or i+1
        tempList.remove(tempList.size() - 1);		// remove the element from list
        used[i] = false;
    }
}
```
