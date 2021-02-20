#include <queue>
#include <stack>
#include <vector>

struct TreeNode {
  int val;
  TreeNode *left;
  TreeNode *right;
  TreeNode() : val(0), left(nullptr), right(nullptr) {}
  TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
  TreeNode(int x, TreeNode *left, TreeNode *right)
      : val(x), left(left), right(right) {}
};

/**************************************************
 *************** preorder traversal **************
 *************************************************/

/**
 * preorder iterative traversal using stack
 */
std::vector<int> preorder_traversal(TreeNode *root) {
  std::stack<TreeNode *> st;
  std::vector<int> res;
  TreeNode *cur = root;
  while (!st.empty() || cur != nullptr) {
    while (cur != nullptr) {
      res.push_back(cur->val);
      st.push(cur);
      cur = cur->left;
    }
    cur = st.top();
    st.pop();
    cur = cur->right;
  }
  return res;
}

/**
 * preorder morris traversal
 */
std::vector<int> preorder_traversal_morris(TreeNode *root) {
  std::vector<int> res;
  TreeNode *cur = root, *mostright = nullptr;
  while (cur != nullptr) {
    mostright = cur->left;
    if (mostright == nullptr)
      res.push_back(cur->val);  // reach the left most node, visit it
    else {
      while (mostright->right != nullptr && mostright->right != cur)
        mostright = mostright->right;
      if (mostright->right == nullptr) {
        res.push_back(cur->val);
        mostright->right =
            cur;  // left subtree has not been visited, visit current node
        cur = cur->left;
        continue;
      } else {
        mostright->right = nullptr;  // left subtree has been visited
      }
    }
    cur = cur->right;
  }
  return res;
}

/**************************************************
 *************** inorder traversal **************
 *************************************************/

/**
 * inorder iterative traversal using stack
 */
std::vector<int> inorder_traversal(TreeNode *root) {
  std::stack<TreeNode *> st;
  std::vector<int> res;
  TreeNode *cur = root;
  while (!st.empty() || cur != nullptr) {
    while (cur != nullptr) {
      st.push(cur);
      cur = cur->left;
    }
    st.pop();
    res.push_back(cur->val);
    cur = cur->right;
  }
  return res;
}

/**
 * inorder morris traversal
 */
std::vector<int> inorder_traversal_morris(TreeNode *root) {
  std::vector<int> res;
  TreeNode *cur = root, *mostright = nullptr;
  while (cur != nullptr) {
    mostright = cur->left;
    if (mostright == nullptr)
      res.push_back(cur->val);  // reach the left most node, visit it
    else {
      while (mostright->right != nullptr && mostright->right != cur)
        mostright = mostright->right;
      if (mostright->right == nullptr) {
        mostright->right = cur;  // left subtree has not been visited
        cur = cur->left;
        continue;
      } else {
        mostright->right =
            nullptr;  // left subtree has been visited, visit current node
        res.push_back(cur->val);
      }
    }
    cur = cur->right;
  }
  return res;
}

/**************************************************
 *************** postorder traversal **************
 *************************************************/

/**
 * postorder traversal using stack
 */
std::vector<int> postorder_traversal(TreeNode *root) {
  std::stack<TreeNode *> st;
  std::vector<int> res;
  TreeNode *cur = root, *lastvisit = nullptr;
  while (!st.empty() || cur != nullptr) {
    while (cur != nullptr) {
      st.push(cur);
      cur = cur->left;
    }
    cur = st.top();
    if (cur->right == nullptr || cur->right == lastvisit) {
      res.push_back(
          cur->val);  // right subtree has been visited, visit current node
      lastvisit = cur;
      st.pop();
      cur = nullptr;
    } else {
      cur = cur->right;  // go to right subtree
    }
  }
  return res;
}

/**
 * postorder morris traversal
 */
void reverse_add(std::vector<int> &, TreeNode *);
std::vector<int> postorderTraversal(TreeNode *root) {
  std::vector<int> res;
  TreeNode *cur = root, *mostright = nullptr;
  while (cur != nullptr) {
    mostright = cur->left;
    if (mostright != nullptr) {
      while (mostright->right != nullptr && mostright->right != cur)
        mostright = mostright->right;
      if (mostright->right == nullptr) {
        mostright->right =
            cur;  // left subtree has not been visited, visit left subtree
        cur = cur->left;
        continue;
      } else {
        mostright->right = nullptr;   // left subtree has been visited
        reverse_add(res, cur->left);  // reverse add right child of left subtree
      }
    }
    cur = cur->right;
  }
  reverse_add(res, root);
  return res;
}

void reverse_add(std::vector<int> &res, TreeNode *cur) {
  int pos = res.size();
  while (cur != nullptr) {
    res.insert(res.begin() + pos, cur->val);
    cur = cur->right;
  }
}

/**************************************************
 *************** level order traversal **************
 *************************************************/

std::vector<std::vector<int>> levelOrder(TreeNode *root) {
  if (!root) { return {}; }
  std::vector<std::vector<int>> result;
  std::queue<TreeNode *> q;
  q.push(root);

  while (!q.empty()) {
    int len = q.size();
    std::vector<int> row;
    while (len-- > 0) {
      if (q.front()->left) {
        q.push(q.front()->left);
      }
      if (q.front()->right) {
        q.push(q.front()->right);
      }
      row.push_back(q.front()->val);
      q.pop();
    }
    result.emplace_back(row);
  }
  return result;
}