
class Node {
 public:
  int val;
  Node* left;
  Node* right;
  Node* next;
  Node() : val(0), left(nullptr), right(nullptr), next(nullptr) {}
  Node(int _val) : val(_val), left(nullptr), right(nullptr), next(nullptr) {}
  Node(int _val, Node* _left, Node* _right, Node* _next)
      : val(_val), left(_left), right(_right), next(_next) {}
};

// recursive solution
Node* connect(Node* root) {
  if (!root) return root;
  if (root->left) root->left->next = root->right;
  if (root->next && root->right && root->next->left)
    root->right->next = root->next->left;
  connect(root->left);
  connect(root->right);
  return root;
}


// iterative solution
void connect2(Node* root) {
  if (!root) return;
  for (Node* leftMost = root; leftMost->left != nullptr;
       leftMost = leftMost->left) {
    for (Node* cur = leftMost; cur != nullptr; cur = cur->next) {
      cur->left->next = cur->right;
      if (cur->next != nullptr) {
        cur->right->next = cur->next->left;
      }
    }
  }
}