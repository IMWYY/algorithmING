
#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * Given a fixed length array arr of integers, duplicate each occurrence of
 * zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written.
 *
 * Do the above modifications to the input array in place, do not return
 * anything from your function.
 */

/**
 * The first we pass forward and count the zeros.
 * The seond we pass backward and assign the values.
 */
void duplicateZeros(vector<int>& A) {
  // j is the index of the result array.
  int len = A.size() + count(A.begin(), A.end(), 0);

  for (int i = A.size() - 1, j = len - 1; i >= 0 && j >= 0; i--, j--) {
    if (A[i] != 0) {
      if (j < A.size()) A[j] = A[i];
    } else {
      if (j < A.size()) A[j] = A[i];
      j--;
      // copy twice when hit '0'
      if (j < A.size()) A[j] = A[i];
    }
  }
}

/**
 * with extra copy of array. O(n) time + O(n) space
 * */
void duplicateZeros(vector<int>& arr) {
  if (arr.size() <= 1) return;

  vector<int> dup(arr);
  vector<int> offsets(arr.size(), 0);

  int cur_offset = 1;
  for (size_t i = 0; i < arr.size(); ++i) {
    if (arr[i] == 0 && i + 1 < offsets.size()) {
      offsets[i + 1] = cur_offset;
      cur_offset++;
    }
  }

  for (size_t i = 0; i < arr.size(); ++i) arr[i] = 0;
  cur_offset = 0;
  for (size_t i = 0; i < arr.size(); ++i) {
    if (cur_offset < offsets[i]) cur_offset = offsets[i];
    if (i + cur_offset < arr.size()) {
      arr[i + cur_offset] = dup[i];
    } else {
      break;
    }
  }
}