#include <stack>
#include <vector>

std::vector<int> dailyTemperatures(std::vector<int>& nums) {
  std::vector<int> res(nums.size(), 0);
  std::stack<int> st;

  for (int i = nums.size() - 1; i >= 0; i--) {
    while (!st.empty() && nums[st.top()] <= nums[i]) {
      st.pop();
    }
    res[i] = st.empty() ? 0 : (st.top() - i);
    st.push(i);
  }
  return res;
}