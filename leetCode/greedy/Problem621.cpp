#include <algorithm>
#include <vector>

/**
 * find the most freqent element to construct the frame (put one element
 * at the first of each interval). Then put remaining elements in empty slots.
 * O(n) time + O(1) space
 */
int leastInterval(std::vector<char>& tasks, int n) {
  std::vector<int> freq(26, 0);
  for (int t : tasks) freq[t - 'A']++;
  std::sort(freq.begin(), freq.end(),
            [](const int& l, const int& r) { return l > r; });
  int max_count = 0;
  while (max_count < freq.size() && freq[max_count] == freq[0]) max_count++;

  int len = std::max(n + 1, max_count);
  int frame = (freq[0] - 1) * len +
              max_count;  // except the empty slots in the last frame
  int empty_slot = (len - max_count) * (freq[0] - 1);
  int remain_task = tasks.size() - freq[0] * max_count;
  int extra = std::max(
      0, remain_task - empty_slot);  // we can simple append these extra tasks
                                     // at the end of each frame
  return frame + extra;
}