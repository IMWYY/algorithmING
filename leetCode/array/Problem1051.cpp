#include <algorithm>
#include <vector>
using namespace std;

/**
 * Height Checker
 * Students are asked to stand in non-decreasing order of heights for an annual
 * photo.
 *
 * Return the minimum number of students not standing in the right positions.
 * (This is the number of students that must move in order for all students to
 * be standing in non-decreasing order of height.)
 */

/**
 * sort the height and then compare element one by one...
 */
int heightChecker(vector<int>& heights) {
  if (heights.size() <= 1) return 0;
  vector<int> v(heights);
  sort(v.begin(), v.end());
  int res = 0;
  for (size_t i = 0; i < v.size(); ++i) {
    if (v[i] != heights[i]) res++;
  }
  return res;
}