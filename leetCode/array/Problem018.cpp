#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <vector>

// solution1: reduce k-sum to 2-sum problem
std::vector<std::vector<int>> fourSum(std::vector<int>& nums, int target) {
  std::sort(nums.begin(), nums.end());
  return kSum(nums, 0, 4, target);
}
std::vector<std::vector<int>> kSum(std::vector<int>& nums, int start, int k,
                                   int target) {
  int len = nums.size();
  std::vector<std::vector<int>> res;
  if (k == 2) {  // two pointers from left and right
    int left = start, right = len - 1;
    while (left < right) {
      int sum = nums[left] + nums[right];
      if (sum == target) {
        std::vector<int> path;
        path.push_back(nums[left]);
        path.push_back(nums[right]);
        res.push_back(path);
        while (left < right && nums[left] == nums[left + 1]) left++;
        while (left < right && nums[right] == nums[right - 1]) right--;
        left++;
        right--;
      } else if (sum < target) {  // move left
        left++;
      } else {  // move right
        right--;
      }
    }
  } else {
    for (int i = start; i < len - (k - 1); i++) {
      if (i > start && nums[i] == nums[i - 1]) continue;  // skip duplicate
      std::vector<std::vector<int>> temp =
          kSum(nums, i + 1, k - 1, target - nums[i]);
      for (auto& t : temp) t.push_back(nums[i]);
      res.insert(res.end(), temp.begin(), temp.end());
    }
  }
  return res;
}

struct myhash {
  size_t operator()(const std::vector<int>& v) const {
    size_t res;
    for (int i = 0; i < v.size(); ++i) {
      res ^= std::hash<int>()(v[i]);
    }
    return res;
  }
};

// solution 2: use unordered_set to deduplicate
std::vector<std::vector<int>> fourSum(std::vector<int>& nums, int target) {
  std::vector<std::vector<int>> res;
  if (nums.size() < 4) return res;
  std::unordered_set<std::vector<int>, myhash> resset;
  std::unordered_map<int, std::vector<std::pair<int, int>>> mp;
  for (int i = 0; i < nums.size(); ++i) {
    for (int j = i + 1; j < nums.size(); ++j) {
      int sum = nums[i] + nums[j];
      if (mp.count(sum) > 0) {
        mp[sum].push_back({i, j});
      } else {
        mp[sum] = {{i, j}};
      }
    }
  }

  for (auto& it : mp) {
    int sum = target - it.first;
    if (mp.count(sum) == 0) continue;
    for (auto& p1 : it.second) {
      for (auto& p2 : mp[sum]) {
        if (p1.first == p2.first || p1.first == p2.second ||
            p1.second == p2.first || p1.second == p2.second)
          continue;
        std::vector<int> tmp = {nums[p1.first], nums[p1.second], nums[p2.first],
                                nums[p2.second]};
        std::sort(tmp.begin(), tmp.end());
        resset.insert(tmp);
      }
    }
  }
  for (auto& v : resset) res.push_back(v);
  return res;
}