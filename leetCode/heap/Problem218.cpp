#include <algorithm>
#include <set>
#include <vector>

std::vector<std::vector<int>> getSkyline(
    std::vector<std::vector<int>>& buildings) {
  std::vector<std::pair<int, int>> points;
  for (auto& v : buildings) {
    points.push_back({v[0], -v[2]});
    points.push_back({v[1], v[2]});
  }
  std::sort(points.begin(), points.end());
  std::vector<std::vector<int>> res;
  std::multiset<int> heights;  // there may be multiple buildings with the same
                               // height, so we use multiset.
  heights.insert(0);
  int cur = 0;
  for (auto& p : points) {
    if (p.second < 0)
      heights.insert(-p.second);
    else
      heights.erase(heights.find(p.second));
    if (cur != *heights.rbegin()) {
      cur = *heights.rbegin();
      res.push_back({p.first, cur});
    }
  }
  return res;
}