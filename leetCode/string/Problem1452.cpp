#include <iostream>
#include <map>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Given the array favoriteCompanies where favoriteCompanies[i] is the list of
 * favorites companies for the ith person (indexed from 0).
 *
 * Return the indices of people whose list of favorite companies is not a subset
 * of any other list of favorites companies. You must return the indices in
 * increasing order.
 */
vector<int> peopleIndexes(vector<vector<string>>& favoriteCompanies) {
  std::vector<int> res;
  vector<unordered_set<string>> companies;
  for (int i = 0; i < favoriteCompanies.size(); ++i) {
    std::unordered_set<string> new_set(favoriteCompanies[i].begin(),
                                       favoriteCompanies[i].end());
    companies.push_back(new_set);
  }
  for (int i = 0; i < companies.size(); ++i) {
    bool is_sub = false;
    for (int j = 0; j < companies.size(); ++j) {
      if (companies[i].size() >= companies[j].size()) continue;
      bool sub = true;
      for (string s : companies[i]) {
        if (companies[j].count(s) == 0) sub = false;
      }
      if (sub) {
        is_sub = true;
        break;
      }
    }
    if (!is_sub) res.push_back(i);
  }
  return res;
}