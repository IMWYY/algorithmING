
#include <bitset>
#include <string>
#include <vector>

using namespace std;

/**
 *
 * Given a string S of lowercase letters, a duplicate removal consists of
 choosing
 * two adjacent and equal letters, and removing them.
 *
 * We repeatedly make duplicate removals on S until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.  It
 is
 * guaranteed the answer is unique.
 */

/**
 * intuitive idea
 */
string removeDuplicates(string S) {
  if (S.size() < 2) return S;
  while (true) {
    bool has_op = false;
    for (int i = 0; i < S.size(); ++i) {
      if (i + 1 < S.size() && S[i] == S[i + 1]) {
        S.erase(i, 2);
        // if (i > 0) i --; // an optimization
        has_op = true;
      }
    }
    if (!has_op) return S;
  }
  return S;
}

/**
 * stack idea. keep res as current result.
 * Loop on characters in the string S one by one.
 * If the next character is the same as the last in res, pop the last character
 * from res. Otherwise append the the next character to the end of res.
 */
string removeDuplicates1(string S) {
  string res = "";
  for (char& c : S)
    if (res.size() && c == res.back())
      res.pop_back();
    else
      res.push_back(c);
  return res;
}