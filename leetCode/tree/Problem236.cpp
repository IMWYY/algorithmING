#include <tree.h>

// return p if p in the tree rooted at @root, or q if q rooted at @root
// or lca if p and q in different subtree
TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
  if (!root || root == p || root == q) return root;
  TreeNode* l = lowestCommonAncestor(root->left, p, q);
  TreeNode* r = lowestCommonAncestor(root->right, p, q);
  if (!l || !r) return !l ? r : l;
  return root;
}