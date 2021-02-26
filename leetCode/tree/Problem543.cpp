#include <tree.h>
#include <vector>

int res = 0;
int depth(TreeNode*);
int diameterOfBinaryTree(TreeNode* root) {
  depth(root);
  return res;
}

int depth(TreeNode* root) {
  if (!root) return 0;
  int l = depth(root->left);
  int r = depth(root->right);
  res = std::max(res, l + r);
  return std::max(l, r) + 1;
}