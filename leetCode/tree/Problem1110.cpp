
#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest
 * (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest.  You may return the
 * result in any order.
 */

struct TreeNode {
  int val;
  TreeNode* left;
  TreeNode* right;
  TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

void traverse(TreeNode* node, std::vector<TreeNode*>& res,
              std::vector<bool>& deleted_node, bool father_deleted);

// logical delete the node and really delete the node when traversing
vector<TreeNode*> delNodes(TreeNode* root, vector<int>& to_delete) {
  std::vector<bool> deleted_node(1000, false);
  for (int& n : to_delete) {
    deleted_node[n] = true;
  }
  std::vector<TreeNode*> res;
  if (!deleted_node[root->val]) res.push_back(root);
  TreeNode *left = root->left, *right = root->right;
  if (root->left != nullptr && deleted_node[root->left->val]) {
    root->left = nullptr;
  }
  if (root->right != nullptr && deleted_node[root->right->val]) {
    root->right = nullptr;
  }
  traverse(left, res, deleted_node, deleted_node[root->val]);
  traverse(right, res, deleted_node, deleted_node[root->val]);
  return res;
}

// if the father of cur_node is deleted and cur_node is not deleted, 
// we add it to the result
void traverse(TreeNode* node, std::vector<TreeNode*>& res,
              std::vector<bool>& deleted_node, bool father_deleted) {
  if (node == nullptr) return;
  TreeNode *left = node->left, *right = node->right;
  if (node->left != nullptr && deleted_node[node->left->val]) {
    node->left = nullptr;
  }
  if (node->right != nullptr && deleted_node[node->right->val]) {
    node->right = nullptr;
  }
  if (father_deleted && !deleted_node[node->val]) {
    res.push_back(node);
  }
  traverse(left, res, deleted_node, deleted_node[node->val]);
  traverse(right, res, deleted_node, deleted_node[node->val]);
}