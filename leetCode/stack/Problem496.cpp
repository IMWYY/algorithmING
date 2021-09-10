#include <stack>
#include <unordered_map>
#include <vector>

// monotonic stack
std::vector<int> nextGreaterElement(std::vector<int>& nums1,
                                    std::vector<int>& nums2) {
  std::vector<int> next_greater(nums2.size(), -1);
  std::vector<int> res(nums1.size(), -1);
  std::stack<int> st;
  std::unordered_map<int, int> mp;

  for (int i = nums2.size() - 1; i >= 0; i--) {
    while (!st.empty() && st.top() <= nums2[i]) {
      st.pop();
    }
    next_greater[i] = (st.empty() ? -1 : st.top());
    st.push(nums2[i]);
  }

  for (int i = 0; i < nums2.size(); ++i) mp[nums2[i]] = i;

  for (int i = 0; i < nums1.size(); ++i) {
    int idx = mp[nums1[i]];
    res[i] = next_greater[idx];
  }

  return res;
}