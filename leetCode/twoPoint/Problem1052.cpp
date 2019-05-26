#include <algorithm>
#include <vector>
using namespace std;

/**
 * Grumpy Bookstore Owner
 * Today, the bookstore owner has a store open for customers.length minutes.
 * Every minute, some number of customers (customers[i]) enter the store, and
 * all those customers leave after the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is
 * grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the
 * bookstore owner is grumpy, the customers of that minute are not satisfied,
 * otherwise they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not grumpy
 * for X minutes straight, but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the
 * day.
 */

/**
 * sliding window solution
 * */
int maxSatisfied(vector<int>& customers, vector<int>& grumpy, int X) {
  int res = 0;
  for (size_t i = 0; i < customers.size(); ++i) {
    if (grumpy[i] == 0) res += customers[i];
  }
  int pre_res = res;
  for (int end = X; end <= customers.size(); ++end) {
    int start = end - X;
    if (start == 0) {
      for (int i = 0; i < end; ++i) {
        if (grumpy[i] == 1) pre_res += customers[i];
      }
    } else {
      if (grumpy[start - 1] == 1) pre_res -= customers[start - 1];
      if (grumpy[end - 1] == 1) pre_res += customers[end - 1];
    }
    res = max(res, pre_res);
  }
  return res;
}