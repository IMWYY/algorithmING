#include <unordered_map>
#include <vector>

class LRUCache {
 public:
  struct Node {
    int key;
    int val;
    Node* next = nullptr;
    Node* prev = nullptr;
    Node(int k, int v) : key(k), val(v) {}
  };

  LRUCache(int cap) : sz(0), cap(cap) {
    head = new Node(-1, -1);
    tail = new Node(-1, -1);
    head->next = tail;
    tail->prev = head;
  }

  ~LRUCache() {
    Node* n = head;
    while (n) {
      Node* tmp = n->next;
      delete n;
      n = tmp;
    }
  }

  void moveToHead(Node* n) {
    if (n->prev) n->prev->next = n->next;
    if (n->next) n->next->prev = n->prev;

    Node* tmp = head->next;
    head->next = n;
    n->prev = head;

    n->next = tmp;
    tmp->prev = n;
  }

  void put(int key, int val) {
    if (mp[key]) {  // the key exists
      Node* n = mp[key];
      n->val = val;
      moveToHead(n);
      return;
    }

    Node* n = nullptr;
    if (sz >= cap) {  // remove the last node
      Node* last = tail->prev;
      if (last->prev) tail->prev = last->prev;
      if (last->prev) last->prev->next = tail;
      n = last;
      mp[n->key] = nullptr;
      n->key = key;
      n->val = val;
      n->prev = nullptr;
      n->next = nullptr;
    } else {
      n = new Node(key, val);
      sz++;
    }

    moveToHead(n);
    mp[key] = n;
  }

  int get(int key) {
    if (!mp[key]) return -1;
    Node* n = mp[key];
    moveToHead(n);
    return n->val;
  }

 private:
  int sz;
  int cap;
  Node* head;
  Node* tail;
  std::unordered_map<int, Node*> mp;
};