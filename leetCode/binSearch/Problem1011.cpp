
#include <vector>
#include <climits>

using namespace std;

/**
 * A conveyor belt has packages that must be shipped from one port to another
 * within D days.
 *
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day,
 * we
 * load the ship with packages on the conveyor belt (in the order given by
 * weights). We may not load more weight than the maximum weight capacity of the
 * ship.
 *
 * Return the least weight capacity of the ship that will result in all the
 * packages on the conveyor belt being shipped within D days.
 *
 */

bool can_ship(vector<int> &weights, int capacity, int D) {
  int d = 0, cap = 0;
  for (auto &j : weights) {
    if (cap + j <= capacity) {
      cap += j;
    } else {
      d++;
      cap = j;
    }
  }
  if (cap > 0) d++;
  return d <= D;
}

// search within the answers
int shipWithinDays(vector<int> &weights, int D) {
  int sum_w = 0, max_w = INT_MIN;
  for (auto &j : weights) {
    sum_w += j;
    max_w = max(max_w, j);
  }
  while (max_w < sum_w) {
    int mid = (max_w + sum_w) / 2;
    if (can_ship(weights, mid, D)) {
      sum_w = mid;
    } else {
      max_w = mid + 1;
    }
  }
  return max_w;
}
