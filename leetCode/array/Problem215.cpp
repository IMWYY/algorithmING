#include <algorithm>
#include <random>
#include <vector>

/***
 * O(N) average and O(N^2) worst
 */
int find(std::vector<int>&, int, int, int);
int findKthLargest(std::vector<int>& nums, int k) {
  // shuffle an array to achieve O(N) worst case
  std::shuffle(nums.begin(), nums.end(),
               std::default_random_engine(time(NULL)));
  return find(nums, 0, nums.size() - 1, k);
}

int find(std::vector<int>& nums, int s, int e, int k) {
  if (s == e) return nums[s];
  int pivot = nums[s];
  int i = s, j = e;
  while (i < j) {
    while (i < j && nums[j] >= pivot) j--;
    while (i < j && nums[i] <= pivot) i++;
    if (i < j) std::swap(nums[i], nums[j]);
  }
  std::swap(nums[i], nums[s]);
  if (e - i + 1 == k) return nums[i];
  if (e - i + 1 < k)
    return find(nums, s, i - 1, k - (e - i + 1));
  else
    return find(nums, i + 1, e, k);
}