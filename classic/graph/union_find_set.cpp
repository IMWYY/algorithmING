#include <assert.h>

#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <string>
#include <unordered_set>
#include <vector>

class UFSet {
 public:
  UFSet(int l) : len(l), parent(l, -1) {}

  int find(int x) {
    std::cout << parent.size() << ": " << x << std::endl;
    std::cout << "[ ";
    for (int i = 0; i < parent.size(); ++i) {
      std::cout << parent[i] << ", ";
    }
    std::cout << "]" << std::endl;
    int s;
    // the value of the root node is negetive, it stores the number of its
    // decendents
    for (s = x; parent[s] >= 0; s = parent[s])
      ;

    std::cout << "mid" << std::endl;
    // fast path for following 'find(x)': directly link all descendents of s as
    // the child of s
    while (parent[x] >= 0) {
      int tmp = parent[x];
      parent[x] = s;
      x = tmp;
    }
    std::cout << "return" << std::endl;
    return s;
  }

  int union_two(int x1, int x2) {
    int r1 = find(x1), r2 = find(x2);
    if (r1 == r2) return -parent[r1];
    int sum = parent[r1] + parent[r2];
    // the # of element in r1 is larger than # of element in r2 (parent[r1] is
    // negetive)
    if (parent[r1] < parent[r2]) {
      parent[r2] = r1;
      parent[r1] = sum;
    } else {
      parent[r1] = r2;
      parent[r2] = sum;
    }
    return -sum;
  }

  bool connected(int x1, int x2) {
    int r1 = find(x1), r2 = find(x2);
    return r1 >= 0 && r1 == r2;
  }

 private:
  std::vector<int> parent;
  const int len;
};
