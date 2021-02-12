#include <array>
#include <string>
#include <vector>

/**
 * Given an array A of strings made only from lowercase letters, return a list
 * of all characters that show up in all strings within the list (including
 * duplicates).  For example, if a character occurs 3 times in all strings but
 * not 4 times, you need to include that character three times in the final
 * answer.
 *
 * You may return the answer in any order.
 */
std::vector<std::string> commonChars(std::vector<std::string> &A) {
  std::vector<std::string> res;
  if (A.empty()) return res;
  std::array<int, 26> cache_a{};
  std::array<int, 26> cache_b{};
  for (char &it : A.at(0)) cache_a[it - 'a']++;

  for (auto &i : A) {
    std::fill(cache_b.begin(), cache_b.end(), 0);
    for (char j : i) {
      if (cache_a[j - 'a'] != 0) cache_b[j - 'a']++;
    }
    for (int k = 0; k < 26; ++k) {
      cache_a[k] = std::min(cache_a[k], cache_b[k]);
    }
  }
  for (int l = 0; l < 26; ++l) {
    for (int k = 0; k < cache_a[l]; ++k) {
      res.emplace_back(1, 'a' + l);
    }
  }

  return res;
}
