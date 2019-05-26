#include <algorithm>
#include <vector>
using namespace std;

/**
 * Given an array A of positive integers (not necessarily distinct), return the
 * lexicographically largest permutation that is smaller than A, that can be
 * made with one swap (A swap exchanges the positions of two numbers A[i] and
 * A[j]).  If it cannot be done, then return the same array.
 */

/**
 * get the largest i that satisfy A[i-1] > A[i],
 * then swap i-1 with the largest j (j >= i) that satisfies A[j] < A[i-1]
 */
vector<int> prevPermOpt1(vector<int>& A) {
  if (A.size() <= 1) return A;
  vector<int> res(A);
  for (int i = res.size() - 1; i > 0; i--) {
    if (res[i - 1] > res[i]) {
      int index = i;
      // NOTE: we should not increase index if it's equal to res[i]
      while (index + 1 < res.size() && res[index + 1] < res[i - 1] &&
             res[index + 1] != res[i])
        index++;
      int temp = res[i - 1];
      res[i - 1] = res[index];
      res[index] = temp;
      break;
    }
  }
  return res;
}