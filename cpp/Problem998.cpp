
#include <iostream>

//
// Created by 王友运 on 2019-02-26.
//
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;

    explicit TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

TreeNode *insertIntoMaxTree(TreeNode *root, int val) {

    if (root && root->val > val) {
        root->right = insertIntoMaxTree(root->right, val);
        return root;
    }
    auto *node = new TreeNode(val);
    node->left = root;
    return node;
}

