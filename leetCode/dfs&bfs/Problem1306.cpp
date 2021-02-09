#include <vector>

/**
 * Given an array of non-negative integers arr, you are initially positioned at
 * start index of the array. When you are at index i, you can jump to i + arr[i]
 * or i - arr[i], check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 */
void dfs(std::vector<int>&, std::vector<bool>&, bool&, int);
bool canReach(std::vector<int>& arr, int start) {
  bool find = false;
  std::vector<bool> visited(arr.size(), false);
  dfs(arr, visited, find, start);
  return find;
}

void dfs(std::vector<int>& arr, std::vector<bool>& visited, bool& find,
         int cur) {
  if (cur < 0 || cur >= arr.size() || find || visited[cur]) return;
  visited[cur] = true;
  if (arr[cur] == 0) find = true;
  dfs(arr, visited, find, cur + arr[cur]);
  dfs(arr, visited, find, cur - arr[cur]);
}
