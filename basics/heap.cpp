#include <assert.h>

#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

template <typename T>
class MinHeap {
 private:
  void swap(int i, int j) {
    T tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
  void heapify_down() {
    int par_idx = 0, size = arr.size();
    while (par_idx * 2 + 1 < size) {
      int child_idx = par_idx * 2 + 1;
      if (child_idx + 1 < size && arr[child_idx + 1] < arr[child_idx]) {
        child_idx++;
      }
      if (arr[child_idx] > arr[par_idx]) {
        break;
      } else {
        swap(child_idx, par_idx);
      }
      par_idx = child_idx;
    }
  }

  void heapify_up() {
    int child_idx = arr.size() - 1, par_idx = -1;
    while (child_idx > 0 && (par_idx = (child_idx - 1) / 2) >= 0) {
      if (arr[par_idx] < arr[child_idx]) {
        break;
      } else {
        swap(par_idx, child_idx);
      }
      child_idx = par_idx;
    }
  }

  std::vector<T> arr;

 public:
  T& top_min() {
    assert(arr.size() > 0);
    return arr[0];
  }

  void pop_min() {
    if (arr.size() == 0) return;
    arr[0] = arr[arr.size() - 1];
    arr.pop_back();
    heapify_down();
    std::cout << "arr.size:" << arr.size() << std::endl;
  }
  void insert(T ele) {
    arr.push_back(ele);
    heapify_up();
    for (int i = 0; i < arr.size(); ++i) std::cout << arr[i] << ", ";
    std::cout << std::endl;
  }
};

int main() {
  MinHeap<int> mh;
  mh.insert(4);
  mh.insert(3);
  mh.insert(1);
  mh.insert(5);
  mh.insert(8);
  mh.insert(0);
  std::cout << mh.top_min() << std::endl;
  mh.pop_min();
  std::cout << mh.top_min() << std::endl;
  mh.pop_min();
  std::cout << mh.top_min() << std::endl;
  mh.pop_min();
  std::cout << mh.top_min() << std::endl;
  mh.pop_min();
  std::cout << mh.top_min() << std::endl;
  mh.pop_min();
  std::cout << mh.top_min() << std::endl;
  mh.pop_min();
}