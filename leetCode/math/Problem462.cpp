#include <algorithm>
#include <vector>

int minMoves2(std::vector<int>& nums) {
  std::sort(nums.begin(), nums.end());
  int s = 0, e = nums.size() - 1;
  int res = 0;
  while (s < e) res += nums[e--] - nums[s++];
  return res;
}