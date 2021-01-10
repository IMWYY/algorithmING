
#include <algorithm>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * Given a list of words, each word consists of English lowercase letters.
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly
 * one letter anywhere in word1 to make it equal to word2.  For example, "abc"
 * is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k]
 * with k >= 1,  where word_1 is a predecessor of word_2, word_2 is a
 * predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the
 * given list of words.
 */

/**
 * Brute-force Solution:
 * first group the words by length. and then run DFS.
 * Remaining Optimization: memorization
 */
// check whether w1 is predecessor of w2
bool is_predecessor(string &w1, string &w2) {
  if (w1.size() + 1 != w2.size()) return false;
  for (size_t i = 0, j = 0; i < w1.size(), j < w2.size();) {
    if (w1[i] == w2[j]) {
      i++;
      j++;
    } else {
      j++;
    }
    if (j - i > 1) return false;
  }
  return true;
}

// calculate the len of longest chain starting with words[len_i][word_i]
int DFS(vector<vector<string>> &words, int len_i, int word_i) {
  string pre_str = words[len_i][word_i];
  int max_len = 1;
  for (size_t i = len_i + 1; i < words.size(); ++i) {
    if (words[i].size() == 0) return max_len;
    for (size_t j = 0; j < words[i].size(); ++j) {
      if (is_predecessor(pre_str, words[i][j])) {
        max_len = max(max_len, 1 + DFS(words, i, j));
      }
    }
  }
  return max_len;
}

int longestStrChain(vector<string> &words) {
  if (words.size() <= 1) return words.size();
  // group all the words according to its length
  vector<vector<string>> all_words(16);
  for (string &s : words) {
    all_words[s.size() - 1].push_back(s);
  }

  int max_len = 1;
  for (int i = 0; i < all_words.size(); ++i) {
    if (all_words[i].size() == 0) continue;
    for (int j = 0; j < all_words[i].size(); ++j) {
      int len = DFS(all_words, i, j);
      max_len = max(len, max_len);
      // note (i + len) may be out of range, so add this condition check
      if (i + len >= all_words.size()) return max_len;
      if (all_words[i + len].size() == 0) {
        i += len;
        break;
      }
    }
  }
  return max_len;
}

/**
 * dynamic programming:
 * sort all the words by its length.
 * for each word, if we find a word with one char missing,
 * then we update the longest chain ending with this word
 */
bool compare(const string &s1, const string &s2) {
  return s1.length() < s2.length();
}

int longestStrChain1(vector<string> &words) {
  sort(words.begin(), words.end(), compare);
  unordered_map<string, int> dp;

  for (string w : words) {
    int chain_len = 0;
    for (int i = 0; i < w.length(); i++) {
      // this substr operation may be a remarkable overhead.
      string word = w.substr(0, i) + w.substr(i + 1);
      chain_len = max(chain_len, dp[word] + 1);
    }
    dp[w] = chain_len;
  }

  int res = 0;
  for (auto m : dp) res = max(res, m.second);

  return res;
}
