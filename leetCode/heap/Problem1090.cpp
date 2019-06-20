
#include <iostream>
#include <multimap>
#include <numeric>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * We have a set of items: the i-th item has value values[i] and label
 * labels[i].
 *
 * Then, we choose a subset S of these items, such that:
 * |S| <= num_wanted
 * For every label L, the number of items in S with label L is <= use_limit.
 * Return the largest possible sum of the subset S.
 *
 * Example 1:
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit
 * = 1 Output: 9 Explanation: The subset chosen is the first, third, and fifth
 * item.
 */

/**
 * sort all items according to its val and get with heap operations.
 */
struct Item {
  int val;
  int lab;
  Item(int v, int l) : val(v), lab(l) {}
  friend bool operator<(const Item& l, const Item& r) { return l.val < r.val; }
};

int largestValsFromLabels(vector<int>& values, vector<int>& labels,
                          int num_wanted, int use_limit) {
  std::vector<Item> items;
  for (size_t i = 0; i < values.size(); ++i) {
    items.emplace_back(values[i], labels[i]);
  }

  make_heap(items.begin(), items.end());
  std::unordered_map<int, int> limits;

  int cur_n = 0, res = 0;
  while (cur_n < num_wanted) {
    if (items.size() == 0) break;
    Item max_item = items.front();
    pop_heap(items.begin(), items.end());
    items.pop_back();

    if (limits.count(max_item.lab) != 0) {
      if (limits[max_item.lab] >= use_limit) {
        continue;
      } else {
        limits[max_item.lab]++;
        cur_n++;
        res += max_item.val;
      }
    } else {
      limits[max_item.lab] = 1;
      cur_n++;
      res += max_item.val;
    }
  }
  return res;
}

/**
 * Greedy Algo.
 * Sort all labels by value. Then, start with the largest value, greedily pick
 * labels. Track how many labels we have used in m, and do not pick that label
 * if we reached the limit.
 */
int largestValsFromLabels1(vector<int>& vs, vector<int>& ls, int wanted,
                           int limit, int res = 0) {
  multimap<int, int> s;
  unordered_map<int, int> m;
  for (auto i = 0; i < vs.size(); ++i) s.insert({vs[i], ls[i]});
  for (auto it = rbegin(s); it != rend(s) && wanted > 0; ++it) {
    if (++m[it->second] <= limit) {
      res += it->first;
      --wanted;
    }
  }
  return res;
}