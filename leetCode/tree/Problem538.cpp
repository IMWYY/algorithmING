#include <tree.h>

// traverse the tree once, keep the sum along the way
int cursum = 0;
TreeNode* convertBST(TreeNode* root) {
  if (!root) return root;
  convertBST(root->right);
  cursum += root->val;
  root->val = cursum;
  convertBST(root->left);
  return root;
}

// solution 2: find the leftmost node of right subtree
// and rightmost node of left subtree
TreeNode* convertBST(TreeNode* root) {
  if (!root) return root;
  convertBST(root->right);
  int offset = 0;

  TreeNode* cur = root->right;
  while (cur && cur->left) cur = cur->left;
  if (cur) offset = cur->val;
  root->val += offset;

  cur = root->left;
  while (cur && cur->right) cur = cur->right;
  if (cur) cur->val += root->val;
  convertBST(root->left);

  return root;
}