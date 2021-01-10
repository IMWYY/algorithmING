#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * A kingdom consists of a king, his children, his grandchildren, and so on.
 * Every once in a while, someone in the family dies or a child is born.
 *
 * The kingdom has a well-defined order of inheritance that consists of the king
 * as the first member. Let's define the recursive function Successor(x,
 * curOrder), which given a person x and the inheritance order so far, returns
 * who should be the next person after x in the order of inheritance.
 *
 * Successor(x, curOrder):
 *     if x has no children or all of x's children are in curOrder:
 *         if x is the king return null
 *         else return Successor(x's parent, curOrder)
 *     else return x's oldest child who's not in curOrder
 */

class ThroneInheritance {
 public:
  ThroneInheritance(std::string kingName) : king(kingName) {}

  void birth(std::string parentName, std::string childName) {
    inheritance[parentName].push_back(childName);
  }

  void death(std::string name) { dead[name] = true; }

  std::vector<std::string> getInheritanceOrder() {
    std::vector<std::string> res;
    dfs(king, res);
    return res;
  }

 private:
  void dfs(std::string cur, std::vector<std::string>& res) {
    if (!dead[cur]) res.push_back(cur);
    for (std::string& s : inheritance[cur]) {
      dfs(s, res);
    }
  }

  std::string king;
  std::unordered_map<std::string, bool> dead;
  std::unordered_map<std::string, std::vector<std::string>> inheritance;
};
