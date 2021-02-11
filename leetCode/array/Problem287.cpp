#include <vector>

/**
 * regard the array as a linked list, and use slow and fast pointer to
 * find the entry point of the circle, which is the duplicated element.
 * O(n) time + O(1) space
 **/
int findDuplicate0(std::vector<int>& nums) {
  int slow = 0, fast = 0;
  while (slow == 0 || slow != fast) {
    slow = nums[slow];
    fast = nums[nums[fast]];
  }
  slow = 0;
  while (slow != fast) {
    slow = nums[slow];
    fast = nums[fast];
  }
  return slow;
}

/**
 * binary search within [1, n] to find the duplicated element.
 * O(nlogn) time + O(1) space
 */
int findDuplicate(std::vector<int>& nums) {
  int s = 1, e = nums.size() - 1;
  while (s < e) {
    int m = (s + e) / 2;
    int cnt = 0;
    for (int i : nums) {
      if (i <= m) cnt++;
    }
    if (cnt <= m)
      s = m + 1;
    else
      e = m;
  }
  return s;
}