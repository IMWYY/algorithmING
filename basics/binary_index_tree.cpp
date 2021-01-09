#include <assert.h>
#include <time.h>

#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

/**
 * binary index tree can anwser range sum query in O(logn) time complexity,
 * while supporting updating element in o(logn) time complexity
 *
 * three cases:
 * case 1: update(idx, val), range_sum(s, e)
 *         basic usage of bit is enough
 * case 2: range_update(s, e, val), query(idx).
 *         1) construct bit on difference array (i.e., d[i] = arr[i] - arr[i-1])
 *         2) range_update(s, e, val) is coverted to
 *         update(s, val) & update(e, -val).
 *         3) query(idx) is converted to range_sum(0, idx)
 * case 3: range_update(s, e, val), range_sum(s, e).
 *         1) construct two difference array, d1: d[i]; d2: i*d[i]
 *         2) range_sum(0, i) = range_sum(d1,0,i) * (i+1) - range_sum(d2,0,i)
 *         3) range_sum(s, e) = range_sum(0, e) - range_sum(0, s)
 */

class BinIndexTree {
 public:
  BinIndexTree(int len) : tree(len + 1, 0) {}

  void upsert(int i, int val) {
    i++;
    while (i < tree.size()) {
      tree[i] += val;
      i += low_bit_val(i);
    }
  }

  // return sum in range [s, e)
  int range_sum(int s, int e) {
    assert(s < e);
    int res = 0;
    if (s == 0) {
      while (e > 0) {
        res += tree[e];
        e -= low_bit_val(e);
      }
      return res;
    }
    return range_sum(0, e) - range_sum(0, s);
  }

  inline int low_bit_val(int n) {
    assert(n >= 0);
    return n & (-n);
  }
  std::vector<int> tree;
};

const int max_round = 10000;
const int max_len = 1000;

int main() {
  srand(time(NULL));
  BinIndexTree bit(max_len);
  std::vector<int> arr(max_len, 0);
  for (int i = 0; i < max_round; ++i) {
    int idx = rand() % max_len;
    int val = rand() % max_round;
    bit.upsert(idx, val);
    arr[idx] += val;
  }

  std::vector<int> prefix_sum(max_len + 1, 0);
  for (size_t i = 0; i < arr.size(); ++i) {
    prefix_sum[i + 1] = prefix_sum[i] + arr[i];
    std::cout << arr[i] << ", prefix_sum[" << i + 1 << "]=" << prefix_sum[i + 1]
              << std::endl;
  }

  for (int i = 0; i < max_len; ++i) {
    for (int j = i + 1; j <= max_len; ++j) {
      int expected = prefix_sum[j] - prefix_sum[i];
      assert(expected == bit.range_sum(i, j));
    }
  }
}
