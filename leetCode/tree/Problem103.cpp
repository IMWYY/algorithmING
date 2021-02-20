#include <tree.h>

#include <vector>
#include <stack>

std::vector<std::vector<int>> zigzagLevelOrder(TreeNode* root) {
  std::vector<std::vector<int>> res;
  if (root == nullptr) return res;
  std::stack<TreeNode*> st;
  st.push(root);
  int level = 1;
  while (!st.empty()) {
    int len = st.size();
    std::vector<int> row;
    std::stack<TreeNode*> tmp_st;
    while (len-- > 0) {
      TreeNode* cur = st.top();
      st.pop();
      if (level % 2 == 1) {
        if (cur->left != nullptr) tmp_st.push(cur->left);
        if (cur->right != nullptr) tmp_st.push(cur->right);
      } else {
        if (cur->right != nullptr) tmp_st.push(cur->right);
        if (cur->left != nullptr) tmp_st.push(cur->left);
      }
      row.push_back(cur->val);
    }
    st.swap(tmp_st);
    level++;
    res.push_back(row);
  }
  return res;
}