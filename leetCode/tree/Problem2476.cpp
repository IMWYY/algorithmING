#include "tree.h"
#include <vector>
#include <algorithm>

void traverse(TreeNode* n, std::vector<int>& v) {
    if (n != nullptr) {
        traverse(n->left, v);
        v.push_back(n->val);
        traverse(n->right, v);
    }
}
std::vector<std::vector<int>> closestNodes(TreeNode* root, std::vector<int>& queries) {
    std::vector<std::vector<int>> res;
    std::vector<int> v;
    traverse(root, v);
    for (int q : queries) {
        auto it = std::lower_bound(begin(v), end(v), q);
        if (it != end(v) && *it == q)
            res.push_back({q, q});
        else
            res.push_back({it == std::begin(v) ? -1 : *std::prev(it), 
	    		   it == std::end(v) ? -1 : *it});
    }
    return res;
}
