#include <string>
#include <vector>

#include "graph_union_find.cpp"

bool equationsPossible(std::vector<std::string>& equations) {
  UFSet uf(26);
  for (auto& s : equations) {
    if (s[1] == '=') {
      if (s[0] == s[3]) continue;
      uf.union_two(s[0] - 'a', s[3] - 'a');
    } else {
      if (s[0] == s[3]) return false;
      if (uf.connected(s[0] - 'a', s[3] - 'a')) return false;
    }
  }
  for (auto& s : equations) {
    if (s[1] == '!' && uf.connected(s[0] - 'a', s[3] - 'a')) return false;
  }
  return true;
}
