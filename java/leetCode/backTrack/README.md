# 回溯法

一个解题模版，参考[这里](https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning))

```java
public List<List<Integer>> solve(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
   	// 对nums数组可以做一些初始化工作，比如排序
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
    if (someCondition) {						// 根据情况在添加进最终结果时候条件判断，比如tempList的长度
    	list.add(new ArrayList<>(tempList));   
    }
    
    for(int i = start; i < nums.length; i++){
        // 有时候需要对元素做一些去重工作，可以利用一个额外的boolean数组来判断该元素是否已经被添加过
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);		// start根据是否可重复等要求，可以是i+1或i或0
        tempList.remove(tempList.size() - 1);		// 将添加的元素remove 避免对递归有影响
    }
}
```



对于重复元素的去除，一般先把数组排序然后逻辑如下：

```java
private void backtrack(List<List<Integer>> list , List<Integer> tempList, int[] nums, int start, boolean[] used){
    if (someCondition) {
    	list.add(new ArrayList<>(tempList));
    }

    for(int i = start; i < nums.length; i++){
        // 这里第二个条件也可以是i > 0 && nums[i] = nums[i-1] && used[i-1] 但是效率低一些
        // 因为前一个已经被用过(used[i-1])的条件下，继续递归，会把已经无法满足条件的路径再走一遍，增加时间复杂度
        // 以[2,2,2]为例画图即可发现问题
        if (used[i] || i > 0 && nums[i] = nums[i-1] && !used[i-1]) continue;
        tempList.add(nums[i]);
        used[i] = true;
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
        used[i] = false;
    }
}
```
