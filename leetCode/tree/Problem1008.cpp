#include <vector>

using namespace std;

/**
 * Return the root node of a binary search tree that matches the given preorder
 * traversal.

 * (Recall that a binary search tree is a binary tree where for every node, any
 * descendant of node.left has a value < node.val, and any descendant of
 * node.right has a value > node.val.  Also recall that a preorder traversal
 * displays the value of the node first, then traverses node.left, then
 * traverses
 * node.right.)
 */

struct TreeNode {
  int val;
  TreeNode* left;
  TreeNode* right;
  TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

TreeNode* bstFromPreorder(vector<int>& preorder) {
  return bstFromPreorder(preorder, 0, preorder.size());
}

TreeNode* bstFromPreorder(vector<int>& preorder, size_t start, size_t end) {
  if (start >= end) return nullptr;
  TreeNode* root = new TreeNode(preorder[start]);
  if (preorder.size() == 1 || end - start == 1) {
    return root;
  }

  size_t mid = start;
  for (; mid < end; ++mid) {
    if (preorder[mid] > preorder[start]) break;
  }
  root->left = bstFromPreorder(preorder, start + 1, mid);
  root->right = bstFromPreorder(preorder, mid, end);
  return root;
}