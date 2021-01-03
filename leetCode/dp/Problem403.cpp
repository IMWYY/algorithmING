#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

/**
 * A frog is crossing a river. The river is divided into some number of units,
 * and at each unit, there may or may not exist a stone. The frog can jump on a
 * stone, but it must not jump into the water.
 *
 * Given a list of stones' positions (in units) in sorted ascending order,
 * determine if the frog can cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assumes the first jump must be
 * 1 unit.
 *
 * If the frog's last jump was k units, its next jump must be either k - 1, k,
 * or k
 * + 1 units. The frog can only jump in the forward direction.
 */
struct meta {
  int idx = -1;
  int step = -1;
  meta(int i, int s) : idx(i), step(s) {}
  bool operator==(const meta& other) const {
    return idx == other.idx && step == other.step;
  }
};

struct MetaHasher {
  size_t operator()(const meta& k) const {
    return (std::hash<int>()(k.idx) ^ (std::hash<int>()(k.step) << 1));
  }
};

bool canJump(std::unordered_map<meta, bool, MetaHasher>& memo,
             std::unordered_set<int>& stones, int idx, int step, int target) {
  int next_idx = idx + step;
  if (stones.count(next_idx) == 0) return false;
  auto it = memo.find({idx, step});
  if (it != memo.end()) {
    return it->second;
  }
  if (step == 0) return false;
  if (next_idx == target) {
    memo.insert({{idx, step}, true});
    return true;
  }
  bool res = canJump(memo, stones, next_idx, step - 1, target) ||
             canJump(memo, stones, next_idx, step, target) ||
             canJump(memo, stones, next_idx, step + 1, target);
  memo.insert({{idx, step}, res});
  return res;
}

bool canCross(std::vector<int>& stones) {
  std::unordered_map<meta, bool, MetaHasher> memo;
  std::unordered_set<int> valid_pos;
  for (int i = 0; i < stones.size(); ++i) {
    memo.insert({{i, (int)stones.size() - 1}, true});
    valid_pos.insert(stones[i]);
  }
  return canJump(memo, valid_pos, 0, 1, stones[stones.size() - 1]);
}