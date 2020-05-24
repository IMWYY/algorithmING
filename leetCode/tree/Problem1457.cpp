#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Given a binary tree where node values are digits from 1 to 9. A path in the
 * Binary tree is said to be pseudo-palindromic if at least one permutation of
 * the Node values in the path is a palindrome.
 *
 * Return the number of pseudo-palindromic paths going from the root node to
 * leaf Nodes.
 */

struct TreeNode {
  int val;
  TreeNode* left;
  TreeNode* right;
  TreeNode() : val(0), left(nullptr), right(nullptr) {}
  TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
  TreeNode(int x, TreeNode* left, TreeNode* right)
      : val(x), left(left), right(right) {}
};

int cnt = 0;

bool is_pseudo_palindromic(std::array<int, 9>& path) {
  bool has_odd = false;
  for (int i = 0; i < path.size(); i++) {
    if (path[i] % 2 == 1) {
      if (has_odd) return false;
      has_odd = true;
    }
  }
  return true;
}

void dfs(TreeNode* node, std::array<int, 9>& path) {
  path[node->val - 1]++;
  if (node->left == nullptr && node->right == nullptr) {
    if (is_pseudo_palindromic(path)) cnt++;
    path[node->val - 1]--;
    return;
  }
  if (node->left != nullptr) dfs(node->left, path);
  if (node->right != nullptr) dfs(node->right, path);
  path[node->val - 1]--;
}

int pseudoPalindromicPaths(TreeNode* root) {
  if (root == nullptr) return 0;
  std::array<int, 9> path = {};
  dfs(root, path);
  return cnt;
}