#include <climits>
#include <numeric>
#include <set>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * Given an array arr that represents a permutation of numbers from 1 to n. You
 * have a binary string of size n that initially has all its bits set to zero.
 *
 * At each step i (assuming both the binary string and arr are 1-indexed) from 1
 * to n, the bit at position arr[i] is set to 1. You are given an integer m and
 * you need to find the latest step at which there exists a group of ones of
 * length m. A group of ones is a contiguous substring of 1s such that it cannot
 * be extended in either direction.
 *
 * Return the latest step at which there exists a group of ones of length
 * exactly m. If no such group exists, return -1.
 */

// iterate forward, keep the length at the bound index.
int findLatestStep(std::vector<int>& arr, int m) {
  int n = arr.size();
  if (m == n) return n;
  std::vector<int> len(n + 2, 0);
  std::vector<int> counter(n + 1, 0);  // counters of a specific length
  int index = -1;
  for (int i = 0; i < arr.size(); ++i) {
    int pos = arr[i];
    int left_len = len[pos - 1], right_len = len[pos + 1];
    len[pos - left_len] = left_len + right_len + 1;
    len[pos + right_len] = left_len + right_len + 1;
    counter[left_len]--;
    counter[right_len]--;
    counter[left_len + right_len + 1]++;
    if (counter[m] > 0) {
      index = i + 1;
    }
  }
  return index;
}

struct pair_hash {
  template <class T1, class T2>
  std::size_t operator()(std::pair<T1, T2> const& pair) const {
    std::size_t h1 = std::hash<T1>()(pair.first);
    std::size_t h2 = std::hash<T2>()(pair.second);
    return h1 ^ h2;
  }
};

// iterate backwards, use a hashset to record the group bounds.
int findLatestStep1(std::vector<int>& arr, int m) {
  if (m == arr.size()) return arr.size();
  std::set<std::pair<int, int>> groups;
  groups.insert({0, arr.size() - 1});
  for (int i = arr.size() - 1; i >= 0; i--) {
    int left = -1, right = -1;
    int target = arr[i] - 1;
    // use upper_bound (binary search internal) to locate the group
    auto it = groups.upper_bound({target, INT_MAX});
    it--;
    left = it->first;
    right = it->second;
    groups.erase(it);
    int new_group = target - left;
    if (new_group == m) return i;
    if (new_group > 0) groups.insert({left, target - 1});
    new_group = right - target;
    if (new_group == m) return i;
    if (new_group > 0) groups.insert({target + 1, right});
  }
  return -1;
}