#include <unordered_map>
#include <vector>

/**
 * solution 1:
 * it's hard to apply sliding window to calculate extactly K distinct elements.
 * but #subarray of extractly k = #subarray of at most k - #subarray of at most
 * k-1
 */
int at_most_k(std::vector<int>&, int);
int subarraysWithKDistinct1(std::vector<int>& A, int K) {
  return at_most_k(A, K) - at_most_k(A, K - 1);
}

int at_most_k(std::vector<int>& A, int K) {
  std::unordered_map<int, int> mp;
  int cnt = 0, res = 0;
  for (int start = 0, end = 0; end < A.size(); ++end) {
    if (mp[A[end]]++ == 0) cnt++;
    while (cnt > K) {
      if (--mp[A[start]] == 0) cnt--;
      start++;
    }
    res += (end - start + 1);
  }
  return res;
}

/**
 * solution 2:
 * get the longest subarray [i, j] with K distinct elements using sliding
 * window. maintain a prefix index p, where [k+p, j] contains K distinct
 * elements then # of subarray within [i, j] with K elements is p + 1.
 */
int subarraysWithKDistinct2(std::vector<int>& A, int K) {
  std::unordered_map<int, int> mp;
  int cnt = 0, res = 0, prefix = 0;
  for (int start = 0, end = 0; end < A.size(); ++end) {
    if (mp[A[end]]++ == 0) cnt++;
    while (cnt > K) {  // new elements added, set prefix to 0
      prefix = 0;
      if (--mp[A[start]] == 0) cnt--;
      start++;
    }
    while (cnt ==
           K) {  // we have the longest subarray ends at end with K elements
      if (mp[A[start]] == 1) break;
      mp[A[start++]]--;
      prefix++;
    }
    if (cnt == K) res += prefix + 1;
  }
  return res;
}