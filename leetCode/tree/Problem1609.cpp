#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

/**
 *
 * A binary tree is named Even-Odd if it meets the following conditions:
 *
 * The root of the binary tree is at level index 0, its children are at level
 * index 1, their children are at level index 2, etc. For every even-indexed
 * level, all nodes at the level have odd integer values in strictly increasing
 * order (from left to right). For every odd-indexed level, all nodes at the
 * level have even integer values in strictly decreasing order (from left to
 * right). Given the root of a binary tree, return true if the binary tree is
 * Even-Odd, otherwise return false.
 */

// Definition for a binary tree node.

struct TreeNode {
  int val;
  TreeNode *left;
  TreeNode *right;
  TreeNode() : val(0), left(nullptr), right(nullptr) {}
  TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
  TreeNode(int x, TreeNode *left, TreeNode *right)
      : val(x), left(left), right(right) {}
};

bool isEvenOddTree(TreeNode *root) {
  std::queue<TreeNode *> q;
  q.push(root);

  int level = -1, size = 1;
  while (!q.empty()) {
    level++;
    int new_size = 0;
    TreeNode *prev = nullptr;
    while (size > 0) {
      TreeNode *cur = q.front();
      q.pop();
      size--;
      if ((level & 1) == (cur->val & 1)) return false;

      if (prev != nullptr) {
        if (((level & 1) == 0 && cur->val <= prev->val) ||
            ((level & 1) == 1 && cur->val >= prev->val)) {
          return false;
        }
      }

      if (cur->left != nullptr) {
        q.push(cur->left);
        new_size++;
      }
      if (cur->right != nullptr) {
        new_size++;
        q.push(cur->right);
      }
      prev = cur;
    }
    size = new_size;
  }
  return true;
}