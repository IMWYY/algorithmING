# 回溯法

一个解题模版，参考[这里](https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning))

```java
public List<List<Integer>> solve(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
   	// 对nums数组可以做一些初始化工作 
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
    if (someCondition) {						// 根据情况在添加进最终结果时候条件判断
    	list.add(new ArrayList<>(tempList));   
    }
    
    for(int i = start; i < nums.length; i++){
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);		// start根据是否可重复等要求，可以是i+1或i
        tempList.remove(tempList.size() - 1);		// 将添加的元素remove 避免对递归有影响
    }
}
```

