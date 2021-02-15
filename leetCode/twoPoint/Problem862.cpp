#include <climits>
#include <deque>
#include <queue>
#include <vector>

/**
 * solution1:
 * keep a increasing prefix sum in a deque, apply sliding window based on
 * the queue.
 * see
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/189039/Detailed-intuition-behind-Deque-solution
 */
int shortestSubarray1(std::vector<int>& A, int K) {
  std::vector<int> presum(A.size() + 1, 0);
  for (int i = 0; i < A.size(); ++i) presum[i + 1] = presum[i] + A[i];
  std::deque<int> q;
  int res = INT_MAX;
  for (int i = 0; i <= A.size(); ++i) {
    while (q.size() > 0 && presum[i] - presum[q.front()] >= K) {
      res = std::min(res, i - q.front());
      // [q.front(), ..., i) has a sum >= K,
      // ==> starting from q.front(), i is the nearest index that has a sum >= K
      // ==> we can pop it
      q.pop_front();
    }
    while (q.size() > 0 && presum[i] <= presum[q.back()]) {
      // keep all elements in deque with an increasing order
      // i > q.back() && presum[i] <= presum[q.back()] ==> it means that we can
      // get a smaller length with a larger sum starting from i compared with
      // starting from q.back() ==> we can pop it
      q.pop_back();
    }
    q.push_back(i);
  }
  return res == INT_MAX ? -1 : res;
}

/**
 * solution2: use a min heap to maintain the prefix sum
 * sum[i+1,...,j] >= K ==>  (presum[j] - presum[i]) >=K
 */
int shortestSubarray2(std::vector<int>& A, int K) {
  std::priority_queue<std::pair<int, int>, std::vector<std::pair<int, int>>,
                      std::greater<std::pair<int, int>>>
      pq;
  int sum = 0, res = INT_MAX;
  pq.push({0, -1});  // <sum, index>, to embrace the case A.size() == 1
  for (int i = 0; i < A.size(); ++i) {
    sum += A[i];
    while (pq.size() > 0 && (sum - pq.top().first) >= K) {
      res = std::min(res, i - pq.top().second);
      pq.pop();  // later i will have a larger length, so we can pop it out
    }
    pq.push({sum, i});
  }
  return res == INT_MAX ? -1 : res;
}