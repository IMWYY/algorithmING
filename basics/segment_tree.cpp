
#include <assert.h>
#include <time.h>

#include <algorithm>
#include <iostream>
#include <vector>

/**
 * Segment tree is more powerful than binary index tree and sparse table.
 * It can be used to answer both range sum and range maximum/minimum.
 * It can support both point update and range update (need to take advantages of
 * lazy update to achieve good performance)
 *
 * logical node layout
 * struct node {
 *   int start; // can be omitted since this can
 *   int end;   // be calucated while tree trasveral
 *   int val;
 *   int mark;  // lazy update
 * };
 *
 * Here we use range sum to build a segment tree.
 */
class SegmentTree {
 public:
  SegmentTree(std::vector<int>& arr) : len(arr.size()) {
    assert(arr.size() > 0);
    assert(seg_tree.size() == 0);
    int max_len = arr.size() * 4;
    seg_tree.reserve(max_len);
    for (size_t i = 0; i < max_len; ++i) {
      seg_tree.push_back(0);
    }
    build(arr, 0, arr.size() - 1, 0);
  }

  /* sum in range [s, e) */
  int range_sum(int arr_s, int arr_e) {
    assert(arr_s < arr_e);
    return range_val(0, 0, len - 1, arr_s, arr_e - 1);
  }

  void update(int idx, int val);
  void range_update(int s, int e, int val);

 private:
  int range_val(int i, int s, int e, int arr_s, int arr_e) {
    assert(s <= e);
    if (arr_s <= s && arr_e >= e) return seg_tree[i];
    if (s == e) return 0;
    int mid = (s + e) / 2;
    int l = 0, r = 0;
    if (mid >= s) {
      l = range_val(2 * i + 1, s, mid, arr_s, arr_e);
    }
    if (mid + 1 <= e) {
      r = range_val(2 * i + 2, mid + 1, e, arr_s, arr_e);
    }
    return l + r;
  }

  int build(std::vector<int>& arr, int s, int e, int i) {
    assert(s <= e && s >= 0);
    assert(e < arr.size());
    if (s == e) {
      seg_tree[i] = arr[s];
      return seg_tree[i];
    }
    int mid = (s + e) / 2;
    int l = build(arr, s, mid, 2 * i + 1);
    int r = build(arr, mid + 1, e, 2 * i + 2);
    seg_tree[i] = l + r;
    return seg_tree[i];
  }

  int len;
  std::vector<int> seg_tree;
};

const int max_len = 1000;

int main() {
  srand(time(NULL));
  std::vector<int> arr(max_len, 0);
  for (int i = 0; i < arr.size(); ++i) {
    arr[i] = rand();
  }

  std::vector<int> prefix_sum(max_len + 1, 0);
  for (size_t i = 0; i < arr.size(); ++i) {
    prefix_sum[i + 1] = prefix_sum[i] + arr[i];
    std::cout << arr[i] << ", prefix_sum[" << i + 1 << "]=" << prefix_sum[i + 1]
              << std::endl;
  }

  SegmentTree segtree(arr);
  //   for (size_t i = 0; i < segtree.seg_tree.size(); ++i) {
  //     std::cout << i << ": " << segtree.seg_tree[i] << std::endl;
  //   }

  for (int i = 0; i < max_len; ++i) {
    for (int j = i + 1; j <= max_len; ++j) {
      int expected = prefix_sum[j] - prefix_sum[i];
      assert(expected == segtree.range_sum(i, j));
    }
  }
}