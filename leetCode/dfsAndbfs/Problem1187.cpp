
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * Given two integer arrays arr1 and arr2, return the minimum number of
 * operations (possibly zero) needed to make arr1 strictly increasing.
 *
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j
 * < arr2.length and do the assignment arr1[i] = arr2[j].
 *
 * If there is no way to make arr1 strictly increasing, return -1.
 *
 */

struct hash_pair {
  template <class T1, class T2>
  size_t operator()(const pair<T1, T2>& p) const {
    auto hash1 = hash<T1>{}(p.first);
    auto hash2 = hash<T2>{}(p.second);
    return hash1 * 37 + hash2;
  }
};

std::unordered_map<std::pair<int, int>, int, hash_pair> memo;

// Here f(i, prev) means the number of modifications
// to make the first i element of arr1 increasing with prev as the last element.
int dfs(std::vector<int>& arr1, vector<int>& arr2, int index, int prev) {
  if (index == arr1.size()) return 0;
  if (memo.count(make_pair(index, prev))) return memo[make_pair(index, prev)];
  int j = std::upper_bound(arr2.begin(), arr2.end(), prev) - arr2.begin();
  // cout << j << " " << prev << endl;
  int res = 0;
  if (arr1[index] > prev) {
    if (j >= arr2.size() || arr2[j] <= prev) {
      res = dfs(arr1, arr2, index + 1, arr1[index]);
    } else {
      res = std::min(dfs(arr1, arr2, index + 1, arr1[index]),
                     1 + dfs(arr1, arr2, index + 1, arr2[j]));
    }
  } else {
    if (j >= arr2.size()) {
      res = 3000;
    } else {
      res = 1 + dfs(arr1, arr2, index + 1, arr2[j]);
    }
  }
  memo[make_pair(index, prev)] = res;
  return res;
}

int makeArrayIncreasing(vector<int>& arr1, vector<int>& arr2) {
  std::sort(arr2.begin(), arr2.end());
  int res = dfs(arr1, arr2, 0, -1);
  if (res >= 3000) return -1;
  return res;
}