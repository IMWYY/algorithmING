
#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all
 * elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1
 are
 * the same as in arr2.  Elements that don't appear in arr2 should be placed at
 the
 * end of arr1 in ascending order.
 *
 * Constraints:
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 */

// Solution1: use constraints. allocate array of 1000, to remember the freq of
// number
vector<int> relativeSortArray1(vector<int>& arr1, vector<int>& arr2) {
  vector<int> dp(1001, 0);
  vector<int> res;
  for (int i = 0; i < arr1.size(); i++)  // store  freq of arr1 elements
    dp[arr1[i]]++;

  for (int i = 0; i < arr2.size(); i++) {
    for (int j = 0; j < dp[arr2[i]]; j++)  // copy arr2 elements first
      res.push_back(arr2[i]);
    dp[arr2[i]] = 0;  // make arr2 elements frequency 0
  }

  for (int i = 0; i <= 1000; i++)
    for (int j = 0; j < dp[i]; j++)
      res.push_back(i);  // copy arr1 elements in order
  return res;
}

// Solution2: intuitive solution
vector<int> relativeSortArray2(vector<int>& arr1, vector<int>& arr2) {
  std::vector<int> res;
  std::vector<int> bitmap(arr1.size(), 0);
  for (int& a : arr2) {
    for (int i = 0; i < arr1.size(); ++i) {
      if (bitmap[i] == 0 && arr1[i] == a) {
        res.push_back(arr1[i]);
        bitmap[i] = 1;
      }
    }
  }
  size_t cur_size = res.size();
  for (int i = 0; i < bitmap.size(); ++i) {
    if (bitmap[i] == 0) res.push_back(arr1[i]);
  }
  std::sort(res.begin() + cur_size, res.end());
  return res;
}