#include <tree.h>

#include <unordered_map>
#include <vector>

/**
 * bottom up solution
 */
std::pair<int, int> sub_rob(TreeNode*);
int rob(TreeNode* root) {
  auto p = sub_rob(root);
  return std::max(p.first, p.second);
}
std::pair<int, int> sub_rob(TreeNode* root) {
  if (!root) return {0, 0};
  auto l = sub_rob(root->left);
  auto r = sub_rob(root->right);
  int include_cur = root->val + l.second + r.second;
  int not_include = std::max(l.first, l.second) + std::max(r.first, r.second);
  return {include_cur, not_include};
}

/**
 * top-down solution: recursive + memorization
 */
struct myhash {
  size_t operator()(const std::pair<TreeNode*, bool>& p) const {
    return std::hash<bool>()(p.second) ^ std::hash<int64_t>()((int64_t)p.first);
  }
};
int sub_rob(std::unordered_map<std::pair<TreeNode*, bool>, int, myhash>&,
            TreeNode*, bool);
int rob2(TreeNode* root) {
  std::unordered_map<std::pair<TreeNode*, bool>, int, myhash> memo;
  return std::max(rob(memo, root, false), rob(memo, root, true));
}

int sub_rob(std::unordered_map<std::pair<TreeNode*, bool>, int, myhash>& memo,
            TreeNode* root, bool include_cur) {
  if (!root) return 0;
  if (memo.count({root, include_cur}) > 0) return memo[{root, include_cur}];
  int res = 0;
  if (include_cur) {
    int l = sub_rob(memo, root->left, false);
    int r = sub_rob(memo, root->right, false);
    res = root->val + l + r;
  } else {
    int l = std::max(sub_rob(memo, root->left, false),
                     sub_rob(memo, root->left, true));
    int r = std::max(sub_rob(memo, root->right, false),
                     sub_rob(memo, root->right, true));
    res = l + r;
  }
  memo[{root, include_cur}] = res;
  return res;
}