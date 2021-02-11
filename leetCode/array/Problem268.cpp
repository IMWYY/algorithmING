#include <vector>

/**
 * xor to find the missing element
 * O(n) time + O(1) space
 * */
int missingNumber(std::vector<int>& nums) {
  int res = nums.size();
  for (int i = 0; i < nums.size(); ++i) {
    res ^= (i ^ nums[i]);
  }
  return res;
}