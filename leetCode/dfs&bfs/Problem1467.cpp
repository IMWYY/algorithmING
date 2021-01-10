#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

// the number of permutation of array balls is
// factorial(sum(balls[i])) / (factorial(balls[0]) * factorial(balls[1]) * ... *
// factorial(balls[k-1]))
double perm(vector<int> &balls) {
  double ans = 1;
  for (int i = 0, j = 1; i < balls.size(); ++i) {
    int n = balls[i];
    for (int k = 1; k <= n; ++k, ++j) ans = ans * j / k;
  }
  return ans;
}

double dfs(vector<int> &balls, vector<int> &a, vector<int> &b, int i, int sa,
           int sb, int sum) {
  if (sa > sum / 2 || sb > sum / 2)
    return 0;  // invalid split because either `a` or `b` takes up more than
               // half of the balls.
  if (i == balls.size()) {
    int ca = 0, cb = 0;
    for (int i = 0; i < balls.size(); ++i) ca += a[i] > 0;
    for (int i = 0; i < balls.size(); ++i) cb += b[i] > 0;
    if (ca != cb)
      return 0;  // invalid split because `a` and `b` don't have the same number
                 // of distinct colors.
    return perm(a) * perm(b);
  }

  double ans = 0;
  for (int j = 0; j <= balls[i]; ++j) {
    a[i] = j;
    b[i] = balls[i] - j;
    ans += dfs(balls, a, b, i + 1, sa + a[i], sb + b[i], sum);
  }
  a[i] = b[i] = 0;  // reset after DFS
  return ans;
}

double getProbability(vector<int> &balls) {
  int sum = accumulate(begin(balls), end(balls), 0);
  vector<int> a(balls.size(), 0), b(balls.size(), 0);
  return dfs(balls, a, b, 0, 0, 0, sum) / perm(balls);
}