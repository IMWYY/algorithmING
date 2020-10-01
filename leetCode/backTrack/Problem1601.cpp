#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * We have n buildings numbered from 0 to n - 1. Each building has a number of
 * employees. It's transfer season, and some employees want to change the
 * building they reside in.
 *
 * You are given an array requests where requests[i] = [fromi, toi] represents
 * an employee's request to transfer from building fromi to building toi.
 *
 * All buildings are full, so a list of requests is achievable only if for each
 * building, the net change in employee transfers is zero. This means the number
 * of employees leaving is equal to the number of employees moving in. For
 * example if n = 3 and two employees are leaving building 0, one is leaving
 * building 1, and one is leaving building 2, there should be two employees
 * moving to building 0, one employee moving to building 1, and one employee
 * moving to building 2.
 *
 * Return the maximum number of achievable requests.
 *
 */

void helper(std::vector<std::vector<int>>&, std::vector<int>&, int, int, int&);

int maximumRequests(int n, std::vector<std::vector<int>>& requests) {
  std::vector<int> count(n, 0);
  int res = 0;
  helper(requests, count, 0, 0, res);
  return res;
}

void helper(std::vector<std::vector<int>>& requests, std::vector<int>& count,
            int i, int choose_n, int& res) {
  if (i == requests.size()) {
    for (int& c : count) {
      if (c != 0) return;
    }
    res = std::max(res, choose_n);
    return;
  }
  count[requests[i][0]]--;
  count[requests[i][1]]++;
  helper(requests, count, i + 1, choose_n + 1, res);
  count[requests[i][0]]++;
  count[requests[i][1]]--;
  if (requests[i][0] != requests[i][1]) {
    helper(requests, count, i + 1, choose_n, res);
  }
}
