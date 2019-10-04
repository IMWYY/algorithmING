#include <algorithm>
#include <array>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>
using namespace std;

// record the occurence of element in map
// then check whether there's duplication
// O(n) time + O(n) space
bool uniqueOccurrences(vector<int>& arr) {
  unordered_map<int, int> m;
  unordered_set<int> s;
  for (auto n : arr) ++m[n];
  for (auto& p : m) s.insert(p.second);
  return m.size() == s.size();
}

// sort and then count
// O(nlogn) time
bool uniqueOccurrences(vector<int>& arr) {
  std::sort(arr.begin(), arr.end());
  std::unordered_set<int> occurence;
  int cnt = 1;
  for (int i = 1; i < arr.size(); ++i) {
    if (arr[i] == arr[i - 1]) {
      cnt++;
    } else {
      if (occurence.count(cnt) > 0) return false;
      occurence.insert(cnt);
      cnt = 1;
    }
  }
  if (occurence.count(cnt) > 0) return false;
  return true;
}
