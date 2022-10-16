#include <vector>
#include <cmath>

long long countSubarrays(std::vector<int>& nums, int minK, int maxK) {
  int j = 0, iMin = -1, iMax = -1;
  long long res = 0;
  for (int i = 0; i < nums.size(); ++i) {
    if (nums[i] < minK || nums[i] > maxK) {
      j = i + 1;
      iMin = iMax = -1;
    }
    if (nums[i] == minK) iMin = i;
    if (nums[i] == maxK) iMax = i;
    res += std::max(0, std::min(iMin, iMax) - j + 1);
  }
  return res;
}