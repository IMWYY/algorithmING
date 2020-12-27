#include <assert.h>

#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

/*
 * find the smallest k >= 0 such that, for all i < k, f(a[i]) == false, and for
 * all j >= k, f(a[j]) == true
 **/
int bisect(std::vector<int> &arr, bool (*f)(int)) {
  int start = 0, end = arr.size();
  // invariant: the target k falls within [start, end]
  // set end to arr.size() to embrace two cases: (1) the 'empty arr'
  // (2) f(all elements) = false
  while (start < end) {
    int mid = start + ((end - start) >> 1);
    if (!f(arr[mid])) {
      start = mid + 1;
    } else {
      end = mid;
    }
  }
  assert(start == end);
  return start;
}

int target = 5;

bool gt(int a) { return a > target; }

bool ge(int a) { return a >= target; }

int main() {
  std::vector<int> arr = {1, 3, 5, 5, 6, 8, 8, 8, 9, 15, 17};

  /* case 1: find the target element */
  int pos = bisect(arr, ge);
  assert(pos == 2);
  std::cout << "[case1] found " << target << " in pos " << pos << std::endl;

  /* case 2: first element greater than target */
  pos = bisect(arr, gt);
  assert(pos == 4);
  std::cout << "[case2] found " << target << " in pos " << pos << std::endl;

  /* case 3: first element greater than or equal to target */
  pos = bisect(arr, ge);
  assert(pos == 2);
  std::cout << "[case3] found " << target << " in pos " << pos << std::endl;

  target = 10;
  /* case 4: insertion position */
  pos = bisect(arr, ge);
  assert(pos == 9);
  std::cout << "[case4] found " << target << " in pos " << pos << std::endl;

  target = 8;
  /* case 5: last element greater than or equal to target  */
  pos = bisect(arr, gt);  // find the first element greater than target
  assert(pos == 8);
  std::cout << "[case5] found " << target << " in pos " << pos - 1 << std::endl;
  // or another way
  int start = 0, end = arr.size();
  while (start < end) {
    int mid = end - ((end - start) >> 1);
    if (arr[mid] > target) {
      end = mid - 1;
    } else {
      start = mid;
    }
  }
  assert(start == 7);
  std::cout << "[case5] found " << target << " in pos " << start << std::endl;
}