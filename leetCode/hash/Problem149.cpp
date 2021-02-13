#include <unordered_map>
#include <vector>

/**
 * use (x,y) after dividing gcd as the key
 */
int gcd(int, int);
int maxPoints(std::vector<std::vector<int>>& points) {
  // two methods to init a map with customed hash function
  // method 1, construct a function object
  //   struct myhash {
  //     size_t operator()(const std::pair<int, int>& p) const {
  //       return std::hash<int>{}(p.first) ^ std::hash<int>{}(p.second);
  //     }
  //   };
  //   std::unordered_map<std::pair<int, int>, int, myhash> mp;
  // method 2, use lambda
  auto h = [](const std::pair<int, int>& p) {
    return std::hash<int>{}(p.first) ^ std::hash<int>{}(p.second);
  };
  std::unordered_map<std::pair<int, int>, int, decltype(h)> mp(points.size(),
                                                               h);
  int res = 0;
  for (int i = 0; i < points.size(); ++i) {
    mp.clear();
    res = std::max(res, 1);
    int duplicate = 1;
    for (int j = i + 1; j < points.size(); ++j) {
      int x = points[j][0] - points[i][0], y = points[j][1] - points[i][1];
      if (x == 0 && y == 0) duplicate++;
    }
    for (int j = i + 1; j < points.size(); ++j) {
      int x = points[j][0] - points[i][0], y = points[j][1] - points[i][1];
      int g = gcd(x, y);
      if (g != 0) {
        x /= g;
        y /= g;
      }
      if (x != 0 || y != 0) mp[{x, y}]++;
      res = std::max(res, mp[{x, y}] + duplicate);
    }
  }
  return res;
}

int gcd(int a, int b) {
  if (b == 0) return a;
  return gcd(b, a % b);
}