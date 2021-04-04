#include <climits>
#include <stack>

#include "tree.h"

// recursive solution
bool isvalid(TreeNode*, int, int, bool, bool);
bool isValidBST(TreeNode* root) {
  return isvalid(root, INT_MIN, INT_MAX, true, true);
}

bool isvalid(TreeNode* root, int lo, int hi, bool inclu_lo, bool inclu_hi) {
  if (!root) return true;
  if ((inclu_lo && root->val < lo) || (!inclu_lo && root->val <= lo) ||
      (inclu_hi && root->val > hi) || (!inclu_hi && root->val >= hi))
    return false;
  return isvalid(root->left, lo, root->val, inclu_lo, false) &&
         isvalid(root->right, root->val, hi, false, inclu_hi);
}

// iterative solution
bool isValidBST2(TreeNode* root) {
  std::stack<TreeNode*> st;
  TreeNode *cur = root, *prev = nullptr;
  while (!st.empty() || cur) {
    while (cur) {
      st.push(cur);
      cur = cur->left;
    }
    if (!st.empty()) {
      cur = st.top();
      st.pop();
      if (prev && cur->val <= prev->val) return false;
      prev = cur;
      cur = cur->right;
    }
  }
  return true;
}