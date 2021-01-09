#include <assert.h>
#include <time.h>

#include <climits>
#include <cmath>
#include <iostream>
#include <vector>

/**
 * sparse table is the typical way to solve the range minimum/maximum query.
 * 1. sutiable for static data
 * 2. O(nlogn) to pre-process data, O(1) for each query
 *
 * construct sparse table @st from @arr
 */
void build_sparse_table(std::vector<int>& arr, std::vector<std::vector<int>>& st) {
  assert(st.size() == 0);
  assert(arr.size() > 0);
  int ilen = (int)arr.size();
  int jlen = (int)std::log2(arr.size()) + 1;
  st.reserve(ilen);

  for (int i = 0; i < ilen; ++i) {
    st.push_back(std::vector<int>(jlen, 0));
    st[i][0] = arr[i];
  }

  // definition: st[i][j] = max{arr[i], arr[i+1], ... arr[i+(1<<j)-1]}
  // the transition equation: st[i][j] = (st[i][j-1], st[i-(1<<(j-1))+1][j-1])

  for (int j = 1; j < jlen; ++j) {  // iterate j first
    for (int i = 0; i + (1 << (j - 1)) < ilen;
         ++i) {  // note the termination condition
      st[i][j] = std::max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
    }
  }
}

std::vector<std::vector<int>> st;

/* return maximum element in [s, e] */
int range_maximum_query(std::vector<int>& arr, int s, int e) {
  if (st.size() == 0) {
    build_sparse_table(arr, st);
    assert(st.size() != 0);
  }
  int loglen = std::log2(e - s + 1);
  return std::max(st[s][loglen], st[e - (1 << loglen) + 1][loglen]);
}

int main() {
  srand(time(NULL));
  const int len = 100;
  std::vector<int> arr(len, 0);
  for (int i = 0; i < len; ++i) {
    arr[i] = rand();
  }

  for (int i = 0; i < arr.size(); ++i) {
    for (int j = i; j < arr.size(); ++j) {
      int expected = INT_MIN;
      for (int k = i; k <= j; ++k) {
        expected = std::max(arr[k], expected);
      }
      std::cout << expected << std::endl;
      assert(expected == range_maximum_query(arr, i, j));
    }
  }
}