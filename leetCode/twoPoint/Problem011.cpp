#include <vector>
/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with
 * x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 * <p>
 */
int maxArea(std::vector<int>& height) {
  int start = 0, end = height.size() - 1;
  int res = 0;
  while (start < end) {
    res = std::max(res, (end - start) * std::min(height[start], height[end]));
    if (height[start] > height[end])
      end--;
    else
      start++;
  }
  return res;
}