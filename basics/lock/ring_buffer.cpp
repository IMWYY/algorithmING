#include <assert.h>

#include <atomic>
#include <vector>

// ---tail---head--
template <typename T>
class array_ring_buffer {
  array_ring_buffer(int sz) : data(sz), head(0), tail(0) {}

  void enqueue(T& element) {
    // TODO
  }

  T dequeue() {
    // TODO
  }

  bool isfull() {
    assert(head >= tail);
    return head - tail == sz;
  }
  bool isempty() {
    assert(head >= tail);
    return head == tail;
  }

 private:
  std::vector<T> data;
  int head;  // producer append data into head
  int tail;  // consumer consume data from tail
};
