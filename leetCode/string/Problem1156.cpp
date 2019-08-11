#include <bitset>
#include <string>
#include <vector>

using namespace std;

/**
 * Given a string text, we are allowed to swap two of the characters in the
 * string. Find the length of the longest substring with repeated characters.
 */

/**
 * loop the array to record the maximum length of
 * repeated characters if we swap the last different one.
 */
int maxRepOpt1(string text) {
  if (text.size() <= 1) return text.size();
  // record the freqency of each char first
  std::vector<int> freq(26, 0);
  for (size_t i = 0; i < text.size(); ++i) {
    freq[text[i] - 'a']++;
  }
  int res = 1, cur_len = 0;
  int index = 0, prev_index = 0;
  char prev = text[0];
  bool has_swap = false;
  while (index < text.size()) {
    char c = text[index];
    if (cur_len == 0) {
      cur_len++;
      prev = c;
      index++;
      continue;
    }

    if (c == prev) {
      cur_len++;
      index++;
      res = max(res, min(cur_len, freq[c - 'a']));
      // cur_len may be larger than freq[c-'a'], which is not allowed
    } else {
      if (!has_swap) {
          // only if there exists another same char can we do swap
        if (freq[prev - 'a'] > cur_len) {
          prev_index = index;
          has_swap = true;
          cur_len++;
          index++;
        } else {
          res = max(res, min(cur_len, freq[prev - 'a']));
          cur_len = 0;
        }
      } else {
        has_swap = false;
        index = prev_index;
        res = max(res, min(cur_len, freq[prev - 'a']));
        cur_len = 0;
      }
    }
  }
  return res;
}