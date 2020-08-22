#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * Given an n x n binary grid, in one step you can choose two adjacent rows of
 * the grid and swap them.
 *
 * A grid is said to be valid if all the cells above the main diagonal are
 * zeros.
 *
 * Return the minimum number of steps needed to make the grid valid, or -1 if
 * the grid cannot be valid.
 *
 * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and
 * ends at cell (n, n).
 */
int zerosAtEnd(std::vector<int> &a);
std::vector<int> gridToVec(std::vector<std::vector<int>> &grid);
int minSwaps(std::vector<int> &a);

// convert to a bubble sorting problem, we need to ensure that rows are sorted
// reversely by # of 0.
// this is a greedy algorithm, see why it works here.
// https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/discuss/768076/Min-Adjacent-Swaps-to-Sort-the-array-of-INTEGERS-with-Proof
int minSwaps(std::vector<std::vector<int>> &grid) {
  std::vector<int> a = gridToVec(grid);
  return minSwaps(a);
}

int zerosAtEnd(std::vector<int> &a) {
  int ans = 0;
  int n = a.size();
  int i = n - 1;
  while (i >= 0 && a[i] == 0) {
    ans++;
    i--;
  }
  return ans;
}

std::vector<int> gridToVec(std::vector<std::vector<int>> &grid) {
  std::vector<int> ans;
  for (auto &x : grid) {
    ans.push_back(zerosAtEnd(x));
  }
  return ans;
}

int minSwaps(std::vector<int> &a) {
  int n = a.size();
  int ans = 0;
  for (int i = 0; i < n; i++) {
    if (a[i] < (n - i - 1)) {
      int j = i;
      while (j < n && a[j] < (n - i - 1)) {  // for each step, we always choose
                                             // the nearest element to swap.
        j++;
      }

      if (j == n) {  // Did not find any number greater than or equal to n-i-1.
        return -1;   // so its impossible to get the answer.
      }
      while (j > i) {
        std::swap(a[j], a[j - 1]);
        ans++;
        j--;
      }
    }
  }
  return ans;
}
