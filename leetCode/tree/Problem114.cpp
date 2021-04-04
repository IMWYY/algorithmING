#include "tree.h"

// iterative solution
void flatten(TreeNode* root) {
  TreeNode *cur = root, *next = nullptr;
  while (cur) {
    if (cur->left) {
      TreeNode* tmp = cur->left;
      while (tmp && tmp->right) {
        tmp = tmp->right;
      }
      tmp->right = cur->right;
      cur->right = cur->left;
      cur->left = nullptr;
    }
    cur = cur->right;
  }
}