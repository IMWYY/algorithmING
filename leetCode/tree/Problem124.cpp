#include <tree.h>
#include <vector>

// recursive solution
int find_path(TreeNode* cur, int& res) {
int maxPathSum(TreeNode* root) {
  if (root == nullptr) return 0;
  int res = root->val;
  find_path(root, res);
  return res;
}

// return the max sum starting from cur node
int find_path(TreeNode* cur, int& res) {
  if (cur == nullptr) return 0;
  int l = std::max(0, find_path(cur->left, res));
  int r = std::max(0, find_path(cur->right, res));
  res = std::max(res, l + cur->val + r); // update result
  return std::max(cur->val, std::max(cur->val + l, cur->val + r));
}