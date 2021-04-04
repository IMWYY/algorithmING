
#include <atomic>

// multiple readers and writers
template <typename T>
class lf_queue {
  struct Node {
    T val;
    Node* next;
  };
  typedef Node node_t;

  lf_queue() {
    node_t* dummy = new node_t;
    head = dummy;
    tail = dummy;
  }

  ~lf_queue() {
    node_t* next;
    while (head) {
      next = head->next;
      delete head;
      head = next;
    }
  }

  void enqueue(T& element) {
    node_t* n = new node_t;
    n->val = element;
    n->next = nullptr;
    node_t* p;

    // set the new node to tail->next
    do {
      p = tail;
    } while (std::atomic_compare_exchange_weak(p->next, nullptr, n));
    // update tail pointer
    std::atomic_compare_exchange_weak(tail, p, n);
  }

  T dequeue() {
    node_t* p;
    // move forward head pointer
    do {
      p = head;
      if (p->next == nullptr) throw "empty queue";
    } while (std::atomic_compare_exchange_weak(head, p, p->next));
    return p->next->val;
  }

 private:
  node_t *head, tail;
};