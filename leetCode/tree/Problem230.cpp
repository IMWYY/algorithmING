#include <tree.h>

int kthSmallest(TreeNode* root, int k) {
  int count = countNodes(root->left);
  if (k <= count) {
    return kthSmallest(root->left, k);
  } else if (k > count + 1) {
    return kthSmallest(root->right,
                       k - 1 - count);  // 1 is counted as current node
  }
  return root->val;
}

int countNodes(TreeNode* n) {
  if (n == nullptr) return 0;
  return 1 + countNodes(n->left) + countNodes(n->right);
}