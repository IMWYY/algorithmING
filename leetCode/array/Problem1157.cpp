#include <bitset>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * Implementing the class MajorityChecker, which has the following API:
 *
 * MajorityChecker(int[] arr) constructs an instance of MajorityChecker with the
 * given array arr; int query(int left, int right, int threshold) has arguments
 * such that: 0 <= left <= right < arr.length representing a subarray of arr; 2
 *
 * threshold > right - left + 1, ie. the threshold is always a strict majority
 of
 * the length of the subarray Each query(...) returns the element in arr[left],
 * arr[left+1], ..., arr[right] that occurs at least threshold times, or -1 if
 no
 * such element exists.
 */


/**
 * put all elements' position into corresponding bucket and
 * binary search the index
 */
class MajorityChecker {
 public:
  std::vector<int> data;
  std::unordered_map<int, std::vector<int>> bucket;

  MajorityChecker(vector<int> &arr) : data(arr) {
    for (int i = 0; i < arr.size(); ++i) {
      if (bucket.count(arr[i]) == 0) {
        std::vector<int> temp;
        temp.push_back(i);
        bucket.emplace(arr[i], temp);
      } else {
        bucket[arr[i]].push_back(i);
      }
    }
  }

  // find first element that greater than or equal to target
  int find_first_ge(std::vector<int> &arr, int target) {
    int l = 0, r = arr.size();
    while (l < r) {
      int m = (l + r) >> 1;
      if (arr[m] < target) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    return l;
  }

  // find first element that greater than target
  int find_first_gt(std::vector<int> &arr, int target) {
    int l = 0, r = arr.size();
    while (l < r) {
      int m = (l + r) >> 1;
      if (arr[m] <= target) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    return l;
  }

  int query(int left, int right, int threshold) {
    int res = data[left], freq = 1;
    for (auto iter = bucket.begin(); iter != bucket.end(); ++iter) {
      std::vector<int> &idx = iter->second;
      int start = find_first_ge(idx, left);
      int end = find_first_gt(idx, right);
      int len = end - start;
      if (len > freq) {
        freq = len;
        res = iter->first;
      }
    }
    return freq >= threshold ? res : -1;
  }
};

/**
 * solution 1: O(n) time + O(n) space
 * Time Limit Exceeded
 */
class MajorityChecker1 {
 public:
  std::vector<int> data;

  MajorityChecker1(vector<int> &arr) : data(arr) {}

  // get the majority element and then check the freqency
  int query(int left, int right, int threshold) {
    int res = data[left], cnt = 1;
    for (int i = left + 1; i <= right; ++i) {
      if (data[i] == res) {
        cnt++;
      } else {
        cnt--;
        if (cnt == 0) {
          res = data[i];
          cnt = 1;
        }
      }
    }
    int freq = 0;
    for (int i = left; i <= right; ++i) {
      if (data[i] == res) freq++;
    }
    return freq >= threshold ? res : -1;
  }
};
