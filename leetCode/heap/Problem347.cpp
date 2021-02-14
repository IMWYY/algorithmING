#include <queue>
#include <unordered_map>
#include <vector>

/**
 * solution 1: hash map + bucket sort
 */
std::vector<int> topKFrequent(std::vector<int>& nums, int k) {
  if (nums.empty()) return {};
  std::vector<int> res;
  std::vector<std::vector<int>> buckets(nums.size() + 1);
  std::unordered_map<int, int> numMap;
  for (int num : nums) numMap[num]++;
  for (auto it = numMap.begin(); it != numMap.end(); it++)
    buckets[it->second].push_back(it->first);
  for (int i = buckets.size() - 1; i >= 0; i--) {
    for (int j = 0; j < buckets[i].size(); j++) {
      res.push_back(buckets[i][j]);
      if (res.size() == k) return res;
    }
  }
  return res;
}

struct meta {
  int v;
  int f;
  bool operator<(const meta& r) const { return f < r.f; }
  bool operator>(const meta& r) const { return f > r.f; }
};

/**
 * solution 2: keep a min heap of size k
 */
std::vector<int> topKFrequent1(std::vector<int>& nums, int k) {
  std::unordered_map<int, int> mp;
  for (int i : nums) mp[i]++;
  // default priority_queue is a max heap
  std::priority_queue<meta, std::vector<meta>, std::greater<meta>> pq;
  for (auto& it : mp) {
    if (pq.size() < k || pq.top().f < it.second) {
      if (pq.size() >= k) pq.pop();
      pq.push({it.first, it.second});
    }
  }
  std::vector<int> res;
  while (!pq.empty()) {
    res.push_back(pq.top().v);
    pq.pop();
  }
  return res;
}

/**
 * solution 3: keep a max heap of size (mp.size - k)
 */
std::vector<int> topKFrequent2(std::vector<int>& nums, int k) {
  std::unordered_map<int, int> mp;
  for (int i : nums) mp[i]++;
  std::priority_queue<std::pair<int, int>> pq;
  std::vector<int> res;
  for (auto& it : mp) {
    pq.push({it.second, it.first});
    if (pq.size() > mp.size() - k) {
      res.push_back(pq.top().second);
      pq.pop();
    }
  }
  return res;
}