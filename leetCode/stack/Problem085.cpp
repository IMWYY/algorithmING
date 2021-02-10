#include <stack>
#include <vector>

/**
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest
 * rectangle containing only 1's and return its area.
 */
// solution 1: dynamic programming
int maximalRectangle(std::vector<std::vector<char>>& matrix) {
  if (matrix.size() == 0 || matrix[0].size() == 0) return 0;
  std::vector<int> height(matrix[0].size(), 0);
  std::vector<int> left(matrix[0].size(), -1);
  std::vector<int> right(matrix[0].size(), matrix[0].size());
  int res = 0;
  for (int r = 0; r < matrix.size(); ++r) {
    int cur_l = -1, cur_r = matrix[0].size();
    for (int c = 0; c < matrix[0].size(); ++c) {
      // update left
      if (matrix[r][c] == '0') {
        cur_l = c;
        left[c] = -1;
      } else
        left[c] = std::max(cur_l, left[c]);

      // update height
      if (matrix[r][c] == '0')
        height[c] = 0;
      else
        height[c]++;
    }
    for (int c = matrix[0].size() - 1; c >= 0; c--) {
      // update right
      if (matrix[r][c] == '0') {
        cur_r = c;
        right[c] = matrix[0].size();
      } else
        right[c] = std::min(cur_r, right[c]);
    }

    for (int c = 0; c < matrix[0].size(); ++c) {
      res = std::max(res, height[c] * (right[c] - left[c] - 1));
    }
  }
  return res;
}

// solution 2
int largestRectangleArea(std::vector<int>&);
int maximalRectangle(std::vector<std::vector<char>>& matrix) {
  if (matrix.size() == 0 || matrix[0].size() == 0) return 0;
  std::vector<int> heights(matrix[0].size(), 0);
  int res = 0;
  for (int r = 0; r < matrix.size(); ++r) {
    for (int c = 0; c < matrix[0].size(); ++c) {
      if (heights[c] == 0 || matrix[r][c] == '0')
        heights[c] = matrix[r][c] - '0';
      else
        heights[c]++;
    }
    res = std::max(res, largestRectangleArea(heights));
  }
  return res;
}

// solution in Problem084
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