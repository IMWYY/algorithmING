#include <stack>
#include <vector>

/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 */
int largestRectangleArea(std::vector<int>& heights) {
  std::stack<int> st;
  int res = 0;
  for (int i = 0; i < heights.size() || !st.empty();) {
    int h = i == heights.size() ? -1 : heights[i];
    if (st.empty() || h >= heights[st.top()]) {
      st.push(i++);
    } else {
      h = heights[st.top()];
      st.pop();
      if (st.empty())
        res = std::max(res, h * i);
      else
        res = std::max(res, h * ((i - 1) - (st.top() + 1) + 1));
      // the right boundary is (i - 1) since i is the smallest index that
      // heights[i] < h
      // the left boundary is (st.top() + 1) since st.top() is the largest index
      // that heights[st.top()] < h
    }
  }
  return res;
}

int largestRectangleArea(std::vector<int> height) {
  if (height.size() == 0) return 0;
  std::vector<int> lessFromLeft(height.size(), 0);
  std::vector<int> lessFromRight(height.size(), 0);
  lessFromRight[height.size() - 1] = height.size();
  lessFromLeft[0] = -1;

  for (int i = 1; i < height.size(); i++) {
    int p = i - 1;
    while (p >= 0 && height[p] >= height[i]) {
      p = lessFromLeft[p];  // this is the important trick, skip these indexes
                            // that have been scaned
    }
    lessFromLeft[i] = p;
  }

  for (int i = height.size() - 2; i >= 0; i--) {
    int p = i + 1;
    while (p < height.size() && height[p] >= height[i]) {
      p = lessFromRight[p];
    }
    lessFromRight[i] = p;
  }

  int maxArea = 0;
  for (int i = 0; i < height.size(); i++) {
    maxArea =
        std::max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
  }

  return maxArea;
}