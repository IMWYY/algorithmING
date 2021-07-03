#include <string>
#include <vector>

// solution1
// split the string by less frequent chars and solve the problem on the
// substring
int longestSubstring1(std::string s, int k) {
  std::vector<int> freq(26, 0);
  for (int i = 0; i < s.size(); ++i) freq[s[i] - 'a']++;

  int res = 0;
  for (int start = 0, end = 0; end < s.size(); end++) {
    if (freq[s[end] - 'a'] < k) {
      res = std::max(res, longestSubstring1(s.substr(start, end - start), k));
      start = end + 1;
    } else {
      if (end == s.size() - 1) {
        if (start == 0)
          res = std::max(res, (int)s.size());
        else
          res = std::max(
              res, longestSubstring1(s.substr(start, end + 1 - start), k));
      }
    }
  }
  return res;
}

// solution2
// we cannot apply the two-pointer template to the original problem, but
// we can use it to solve a sub-problem.
int longestSubstring2(std::string s, int k) {
  int res = 0;
  for (int num_unique_char = 1; num_unique_char <= 26; ++num_unique_char) {
    // find the longest substring with numUniqueTarget unique chars, and each
    // char appears at least k times.
    std::vector<int> freq(26, 0);
    int cnt_unique = 0, cnt_noless_k = 0;

    for (int start = 0, end = 0; end < s.size(); end++) {
      if (freq[s[end] - 'a'] == 0) cnt_unique++;
      freq[s[end] - 'a']++;
      if (freq[s[end] - 'a'] == k) cnt_noless_k++;

      while (cnt_unique > num_unique_char) {
        if (freq[s[start] - 'a'] == k) cnt_noless_k--;
        freq[s[start] - 'a']--;
        if (freq[s[start] - 'a'] == 0) cnt_unique--;
        start++;
      }
      if (cnt_unique == cnt_noless_k) res = std::max(res, end - start + 1);
    }
  }
  return res;
}