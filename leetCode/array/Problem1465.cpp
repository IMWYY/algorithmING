#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 *
 * Given a rectangular cake with height h and width w, and two arrays of
 * integers horizontalCuts and verticalCuts where horizontalCuts[i] is the
 * distance from the top of the rectangular cake to the ith horizontal cut and
 * similarly, verticalCuts[j] is the distance from the left of the rectangular
 * cake to the jth vertical cut.
 *
 * Return the maximum area of a piece of cake after you cut at each horizontal
 * and vertical position provided in the arrays horizontalCuts and verticalCuts.
 * Since the answer can be a huge number, return this modulo 10^9 + 7.
 */

int maxArea(int h, int w, vector<int>& horizontalCuts,
            vector<int>& verticalCuts) {
  std::sort(horizontalCuts.begin(), horizontalCuts.end());
  std::sort(verticalCuts.begin(), verticalCuts.end());
  int max_h = horizontalCuts[0], max_w = verticalCuts[0];
  for (int i = 1; i < horizontalCuts.size(); ++i) {
    max_h = std::max(max_h, horizontalCuts[i] - horizontalCuts[i - 1]);
  }
  max_h = std::max(max_h, h - horizontalCuts[horizontalCuts.size() - 1]);
  for (int i = 1; i < verticalCuts.size(); ++i) {
    max_w = std::max(max_w, verticalCuts[i] - verticalCuts[i - 1]);
  }
  max_w = std::max(max_w, w - verticalCuts[verticalCuts.size() - 1]);
  long a = 1e9 + 7;
  long b = (max_h % a) * (max_w % a);
  return b % a;
}