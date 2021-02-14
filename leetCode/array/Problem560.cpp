#include <unordered_map>
#include <vector>

/**
 * solution 1: sum[i,..,j] = sum[0,..,i] + sum[0,...,j] = k
 * use one map to record the prefix sum
 */
int subarraySum1(std::vector<int>& nums, int k) {
  int sum = 0, result = 0;
  std::unordered_map<int, int> presum;
  presum[0] = 1;

  for (int i = 0; i < nums.size(); i++) {
    sum += nums[i];
    // get the number indexes j where sum[0..j] = sum -k
    // --> sum[j, ... ,i] = k
    if (presum.count(sum - k) > 0) {
      result += presum[sum - k];
    }
    presum[sum]++;
  }

  return result;
}

/**
 * solution 2: reuse prefix sum
 */
int subarraySum2(std::vector<int>& nums, int k) {
  std::unordered_map<int, int> mp;
  int sum = 0;
  // get the prefix sum starting from index 0
  for (int i = 0; i < nums.size(); ++i) {
    sum += nums[i];
    mp[sum]++;
  }
  int res = 0, bias = 0;
  for (int i = 0; i < nums.size(); ++i) {
    if (mp.count(k + bias)) res += mp[k + bias];
    // get the prefix sum starting from index i by bias
    bias += nums[i];
    if (mp.count(bias) > 0) mp[bias]--;
  }
  return res;
}
