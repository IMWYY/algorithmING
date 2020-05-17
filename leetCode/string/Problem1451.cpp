#include <iostream>
#include <map>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 *Given a sentence text (A sentence is a string of space-separated words) in the
 * following format:
 *
 * First letter is in upper case.
 * Each word in text are separated by a single space.
 * Your task is to rearrange the words in text such that all words are
 * rearranged in an increasing order of their lengths. If two words have the
 *same length, arrange them in their original order.
 *
 * Return the new text following the format shown above.
 */
string arrangeWords(string text) {
  std::map<int, std::string> pos_map;

  std::string res;
  int pre_pos = 0;
  int pos = text.find(' ', pre_pos);
  while (pos != std::string::npos) {
    int len = pos - pre_pos;
    std::string next = text.substr(pre_pos, len);
    if (pre_pos == 0) {
      next.replace(next.begin(), next.begin() + 1, 1, tolower(next[0]));
    }
    if (pos_map.count(len) == 0) {
      pos_map[len] = next;
    } else {
      pos_map[len].append(1, ' ');
      pos_map[len].append(next);
    }

    pre_pos = pos + 1;
    pos = text.find(' ', pre_pos);
    if (pos == std::string::npos && pre_pos < text.size()) {
      pos = text.size();
    }
  }

  bool first = true;
  for (auto it = pos_map.begin(); it != pos_map.end(); it++) {
    if (first) {
      res.append(it->second);
      first = false;
    } else {
      res.append(1, ' ').append(it->second);
    }
  }
  if (res.size() > 0)
    res.replace(res.begin(), res.begin() + 1, 1, toupper(res[0]));
  return res;
}