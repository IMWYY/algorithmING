#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

/**
 * You are given a list of preferences for n friends, where n is always even.
 *
 * For each person i, preferences[i] contains a list of friends sorted in the
 * order of preference. In other words, a friend earlier in the list is more
 * preferred than a friend later in the list. Friends in each list are denoted
 * by integers from 0 to n-1.
 *
 * All the friends are divided into pairs. The pairings are given in a list
 * pairs, where pairs[i] = [xi, yi] denotes xi is paired with yi and yi is
 * paired with xi.
 *
 * However, this pairing may cause some of the friends to be unhappy. A friend x
 * is unhappy if x is paired with y and there exists a friend u who is paired
 * with v but:
 *
 * x prefers u over y, and
 * u prefers x over v.
 * Return the number of unhappy friends.
 *
 */

int unhappyFriends(int n, std::vector<std::vector<int>>& preferences,
                   std::vector<std::vector<int>>& pairs) {
  std::vector<std::vector<int>> pref_map(n, std::vector<int>(n, 0));
  for (int i = 0; i < preferences.size(); ++i) {
    std::vector<int>& p = preferences[i];
    for (int j = 0; j < p.size(); ++j) {
      pref_map[i][p[j]] = j;
    }
  }

  int res = 0;
  for (std::vector<int>& p : pairs) {
    bool found0 = false, found1 = false;
    for (std::vector<int>& q : pairs) {
      if (found0 && found1) break;
      if (q[0] == p[0]) continue;
      if (!found0 && ((pref_map[p[0]][q[0]] < pref_map[p[0]][p[1]] &&
                       pref_map[q[0]][p[0]] < pref_map[q[0]][q[1]]) ||
                      (pref_map[p[0]][q[1]] < pref_map[p[0]][p[1]] &&
                       pref_map[q[1]][p[0]] < pref_map[q[1]][q[0]]))) {
        res++;
        found0 = true;
      }
      if (!found1 && ((pref_map[p[1]][q[0]] < pref_map[p[1]][p[0]] &&
                       pref_map[q[0]][p[1]] < pref_map[q[0]][q[1]]) ||
                      (pref_map[p[1]][q[1]] < pref_map[p[1]][p[0]] &&
                       pref_map[q[1]][p[1]] < pref_map[q[1]][q[0]]))) {
        res++;
        found1 = true;
      }
    }
  }
  return res;
}