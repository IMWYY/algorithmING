#include <vector>

void sortColors(std::vector<int>& nums) {
  int low = 0, high = nums.size() - 1;
  for (int i = 0; low <= high && i <= high;) {
    if (nums[i] == 0) {
      std::swap(nums[low], nums[i]);
      low++;
      i++;
    } else if (nums[i] == 2) {
      std::swap(nums[high], nums[i]);
      high--;
    } else {
      i++;
    }
  }
}