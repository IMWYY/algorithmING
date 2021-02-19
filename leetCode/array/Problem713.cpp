#include <stdlib.h>

#include <climits>
#include <vector>

int numSubarrayProductLessThanK(std::vector<int>& nums, int k) {
  if (k == 0 || k == 1) return 0;
  int res = 0;
  int64_t pro = 1;
  for (int start = 0, end = 0; end < nums.size();) {
    if (pro * (int64_t)nums[end] > INT_MAX || pro * nums[end] >= k) {
      if (start == end) {
        start++;
        end++;
      } else {
        pro /= nums[start];
        start++;
      }
    } else {
      pro *= nums[end];
      res += (end - start + 1);
      end++;
    }
  }
  return res;
}