#include <stack>
#include <vector>

/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it can trap after raining.
 */

/* solution1: stack-based*/
int trap1(std::vector<int>& height) {
  std::stack<int> st;
  int left = 0, res = 0;
  for (int i = 0; i < height.size(); ++i) {
    int cur = height[i];
    int h = std::min(cur, left);
    int cnt = 0;
    while (!st.empty() && h > st.top()) {
      res += (h - st.top());
      st.pop();
      cnt++;
    }
    if (cur < left) {
      while (cnt-- > 0) st.push(h);
      if (st.empty()) left = cur;
    } else {
      while (!st.empty() && left >= st.top()) st.pop();
      left = cur;
    }
    st.push(cur);
  }
  return res;
}

/* solution2: record the left bound and right bound for each bin*/
int trap2(std::vector<int>& height) {
  if (height.size() == 0) return 0;
  std::vector<int> lh(height.size(), 0), rh(height.size(), 0);
  int i = 0, res = 0;
  for (i = 1, lh.front() = height.front(); i < height.size(); ++i)
    lh[i] = std::max(lh[i - 1], height[i]);
  for (i = height.size() - 2, rh.back() = height.back(); i >= 0; i--)
    rh[i] = std::max(rh[i + 1], height[i]);
  for (int i = 0; i < height.size(); ++i)
    res += std::min(lh[i], rh[i]) - height[i];
  return res;
}

/* solution3: two pointers */
int trap3(std::vector<int>& height) {
  int left = 0;
  int right = height.size() - 1;
  int res = 0;
  int maxleft = 0, maxright = 0;
  while (left <= right) {
    if (height[left] <= height[right]) {
      if (height[left] >= maxleft)
        maxleft = height[left];
      else
        res += maxleft - height[left];
      left++;
    } else {
      if (height[right] >= maxright)
        maxright = height[right];
      else
        res += maxright - height[right];
      right--;
    }
  }
  return res;
}