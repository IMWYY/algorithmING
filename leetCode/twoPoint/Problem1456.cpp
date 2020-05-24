#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Given a string s and an integer k.
 * Return the maximum number of vowel letters in any substring of s with length
 * k.
 * Vowel letters in English are (a, e, i, o, u).
 */

bool is_vowels(char c) {
  return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
}

int maxVowels(string s, int k) {
  int res = 0, start = 0;
  for (int i = 0; i < (k < s.size() ? k : s.size()); ++i) {
    if (is_vowels(s[i])) res++;
  }
  start++;
  int cur_res = res;
  while (start + k <= s.size()) {
    if (is_vowels(s[start - 1])) cur_res--;
    if (is_vowels(s[start + k - 1])) cur_res++;
    res = std::max(res, cur_res);
    start++;
  }
  return res;
}