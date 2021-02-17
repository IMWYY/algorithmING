#include <tree.h>

int height(TreeNode*);

int countNodes(TreeNode* root) {
  if (root == nullptr) return 0;
  int h = height(root);
  if (h <= 1) return h;
  return height(root->right) == h - 1 ? (1 << h - 1) + countNodes(root->right)
                                      : (1 << h - 2) + countNodes(root->left);
}

int height(TreeNode* node) {
  int result = 0;
  while (node != nullptr) {
    result++;
    node = node->left;
  }
  return result;
}