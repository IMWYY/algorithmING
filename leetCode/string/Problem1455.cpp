#include <cmath>
#include <iostream>
#include <numeric>
#include <sstream>
#include <string>
#include <unordered_set>
#include <vector>

/**
 * Given a sentence that consists of some words separated by a single space, and
 * a searchWord.
 * You have to check if searchWord is a prefix of any word in sentence.
 * Return the index of the word in sentence where searchWord is a prefix of this
 * word (1-indexed).
 * If searchWord is a prefix of more than one word, return the index of the
 * first word (minimum index). If there is no such word return -1.
 * A prefix of a string S is any leading contiguous substring of S.
 */

using namespace std;
int isPrefixOfWord(string s, string w) {
  istringstream ss(s);
  string word;
  for (int i = 1; ss >> word; ++i) {
    if (word.size() >= w.size() && word.substr(0, w.size()) == w) return i;
  }
  return -1;
}

// O(n) time + O(1) space
int isPrefixOfWord2(string sentence, string searchWord) {
  auto sent = " " + sentence, word = " " + searchWord;
  int word_cnt = 0;
  for (auto i = 0, j = 0; i < sent.size(); ++i) {
    word_cnt += sent[i] == ' ';
    j = sent[i] == word[j] ? j + 1 : 0;
    if (j == word.size()) return word_cnt;
  }
  return -1;
}