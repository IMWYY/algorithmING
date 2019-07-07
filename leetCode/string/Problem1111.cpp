
#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * A string is a valid parentheses string (denoted VPS) if and only if it
 * consists of "(" and ")" characters only, and:
 *
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are VPS's, or
 * It can be written as (A), where A is a VPS.
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 *
 * depth("") = 0
 * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
 * depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 *
 * For example,  "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1,
 * and 2), and ")(" and "(()" are not VPS's.
 *
 * Given a VPS seq, split it into two disjoint subsequences A and B, such that A
 * and B are VPS's (and A.length + B.length = seq.length).
 *
 * Now choose any such A and B such that max(depth(A), depth(B)) is the minimum
 * possible value.
 *
 * Return an answer array (of length seq.length) that encodes such a choice of A
 * and B:  answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that
 * even though multiple answers may exist, you may return any of them.
 */

// the depth of the VPS is the length of continuous '('.
// so we first calculate the depth of seq, and then find a
// subseq whose depth is depth_of(seq) / 2.
vector<int> maxDepthAfterSplit(string seq) {
  std::vector<int> res(seq.size(), 0);
  int max_d_index = 0, cur_d = 0, max_d = 0;
  for (int i = 0; i < seq.size(); ++i) {
    if (seq[i] == '(') {
      cur_d++;
      if (cur_d > max_d) {
        max_d = cur_d;
        max_d_index = i;
      }
    } else {
      cur_d = 0;
    }
  }

  // pick the second half of the continuous '('
  int depth_a = max_d / 2;
  for (int i = 0; i < depth_a; ++i) res[max_d_index - i] = 1;

  // find corresponding '('
  int cur_pair_n = 0, a_right_n = 0;
  for (int i = max_d_index + 1; i < seq.size() && a_right_n < depth_a; ++i) {
    if (seq[i] == '(') {
      cur_pair_n++;
    } else {
      if (cur_pair_n == 0) {
        res[i] = 1;
        a_right_n++;
      } else {
        cur_pair_n--;
      }
    }
  }

  return res;
}