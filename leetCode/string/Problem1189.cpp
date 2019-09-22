#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * Given a string text, you want to use the characters of text to form as many
 * instances of the word "balloon" as possible.
 * 
 * You can use each character in text at most once. Return the maximum number of
 * instances that can be formed.
 */

// count the number of specific character
int maxNumberOfBalloons(string text) {
  std::vector<int> freq(26, 0);
  for (int i = 0; i < text.size(); ++i) {
    freq[text[i] - 'a']++;
  }

  std::string balloon = "balloon";
  int cnt = INT_MAX;
  for (int i = 0; i < balloon.size(); ++i) {
    if (balloon[i] == 'l' || balloon[i] == 'o') {
      cnt = std::min(freq[balloon[i] - 'a'] / 2, cnt);
    } else {
      cnt = std::min(freq[balloon[i] - 'a'], cnt);
    }
  }
  return cnt;
}