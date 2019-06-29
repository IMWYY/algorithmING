#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * (This problem is an interactive problem.)
 *
 * You may recall that an array A is a mountain array if and only if:
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that
 * mountainArr.get(index) == target.  If such an index doesn't exist, return -1.
 *
 * You can't access the mountain array directly.  You may only access the array
 * using a MountainArray interface:
 *
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged
 * Wrong Answer.  Also, any solutions that attempt to circumvent the judge will
 * result in disqualification.
 */

class MountainArray {
 public:
  int get(int index);
  int length();
};

// Binary find peak in the mountain, Binary find the target in strict increasing
// array and strict decreasing array.
// (just a tip)
// If I want find the index, I always use while (left < right)
// If I may return the index during the search, I'll use while (left <= right)
int findInMountainArray(int target, MountainArray A) {
  int n = A.length(), l, r, m, peak = 0;
  // find index of peak
  l = 0;
  r = n - 1;
  while (l < r) {
    m = (l + r) / 2;
    if (A.get(m) < A.get(m + 1))
      l = peak = m + 1;
    else
      r = m;
  }
  // find target in the left of peak
  l = 0;
  r = peak;
  while (l <= r) {
    m = (l + r) / 2;
    if (A.get(m) < target)
      l = m + 1;
    else if (A.get(m) > target)
      r = m - 1;
    else
      return m;
  }
  // find target in the right of peak
  l = peak;
  r = n - 1;
  while (l <= r) {
    m = (l + r) / 2;
    if (A.get(m) > target)
      l = m + 1;
    else if (A.get(m) < target)
      r = m - 1;
    else
      return m;
  }
  return -1;
}

// find the sorted range and than binary search the range
int findInMountainArray(int target, MountainArray &mountainArr) {
  int len = mountainArr.length();
  int head = mountainArr.get(0), tail = mountainArr.get(len - 1);
  if (head == target) return 0;
  if (head > target && tail > target) return -1;

  bool exist_in_right = false;
  // the index must fall into the right part if exist.
  if (head > tail && head > target) exist_in_right = true;

  int l = 0, r = len;
  while (l < r) {
    int mid = (l + r) / 2;
    int mid_v = mountainArr.get(mid);
    if (mid_v == target) return mid;

    if (mid + 1 >= len) {
      // NOTE here when mid+1 >= len, l = len-1 or len, so we should check the
      // index l to ensure correctness
      if (mountainArr.get(l) == target) return l;
      return -1;
    }

    int mid_r_v = mountainArr.get(mid + 1);
    if (mid_r_v == target) return mid + 1;

    if (mid_v > target) {
      if (mid_r_v > mid_v) {
        r = mid;
        break;
      } else {
        if (mid_r_v > target) {
          if (exist_in_right) {
            l = mid + 2;
            break;
          } else {
            r = mid;
          }
        } else {
          r = mid;
        }
      }
    } else {  // mid_v < target
      if (mid_r_v > mid_v)
        l = mid + 2;
      else
        r = mid;
    }
  }

  while (l < r) {
    int mid = (l + r) / 2;
    int mid_v = mountainArr.get(mid);
    if (mid_v == target)
      return mid;
    else if (mid_v > target)
      r = mid;
    else
      l = mid + 1;
  }
  return -1;
}