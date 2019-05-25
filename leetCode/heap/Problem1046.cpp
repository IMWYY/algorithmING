
#include <vector>
#include <algorithm>

using namespace std;
/**
 * We have a collection of rocks, each rock has a positive integer weight.
 * Each turn, we choose the two heaviest rocks and smash them together.  Suppose
 * the stones have weights x and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of
 * weight y has new weight y-x. At the end, there is at most 1 stone left.
 * Return the weight of this stone (or 0 if there are no stones left.)
 */

int lastStoneWeight(vector<int>& stones) {
  if (stones.size() == 0) return 0;
  if (stones.size() == 1) return stones[0];
  make_heap(stones.begin(), stones.end());
  while (stones.size() > 1) {
    int y = stones.front();
    pop_heap(stones.begin(), stones.end());
    stones.pop_back();
    int x = stones.front();
    pop_heap(stones.begin(), stones.end());
    stones.pop_back();
    if (y > x) {
      stones.push_back(y - x);
      push_heap(stones.begin(), stones.end());
    }
  }
  if (stones.size() == 0)
    return 0;
  else
    return stones[0];
}