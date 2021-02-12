#include <stdlib.h>

#include <vector>

// Fisher–Yates shuffle algorithm
class Solution {
 public:
  std::vector<int> arr;

  Solution(std::vector<int>& nums) : arr(nums) {}

  /** Resets the array to its original configuration and return it. */
  std::vector<int> reset() { return arr; }

  /** Returns a random shuffling of the array. */
  std::vector<int> shuffle() {
    std::vector<int> res(arr);
    for (int i = 0; i < res.size(); ++i) {
      int idx = rand() % (i + 1);
      std::swap(res[idx], res[i]);
    }
    return res;
  }
};