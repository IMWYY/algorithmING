#include <unordered_map>
#include <vector>

/**
 * Given two arrays, write a function to compute their intersection.
 */
std::vector<int> intersect(std::vector<int>& a, std::vector<int>& b) {
  std::unordered_map<int, int> ctr;
  for (int i : a) ctr[i]++;
  std::vector<int> out;
  for (int i : b)
    if (ctr[i]-- > 0) out.push_back(i);
  return out;
}