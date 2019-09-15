#include <bitset>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * Given an array of integers, return the maximum sum for a non-empty subarray
 * (contiguous elements) with at most one element deletion. In other words, you
 * want to choose a subarray and optionally delete one element from it so that
 * there is still at least one element left and the sum of the remaining
 * elements is maximum possible.
 *
 * Note that the subarray needs to be non-empty after deleting one element.
 */

// similar to maximum sum of subarray.
// Compute maxEndHere and maxStartHere arrays and also find overall max along
// with them. Now, evaluate the case where 1-element can be eliminated, that is
// at each index, we can make use of maxEndHere[i-1]+maxStartHere[i+1]
int maximumSum(vector<int>& arr) {
  std::vector<int> max_end_here(arr.size(), 0);
  std::vector<int> max_start_here(arr.size(), 0);

  int result = arr[0];
  max_end_here[0] = arr[0];
  for (int i = 1; i < arr.size(); ++i) {
    max_end_here[i] = max(arr[i], max_end_here[i - 1] + arr[i]);
    result = std::max(result, max_end_here[i]);
  }

  max_start_here[max_start_here.size() - 1] = arr[arr.size() - 1];
  for (int i = arr.size() - 2; i >= 0; i--) {
    max_start_here[i] = std::max(arr[i], max_start_here[i + 1] + arr[i]);
    result = std::max(result, max_start_here[i]);
  }

  for (int i = 1; i < arr.size() - 1; ++i) {
    result = std::max(result, (max_start_here[i + 1] + max_end_here[i - 1]));
  }

  return result;
}