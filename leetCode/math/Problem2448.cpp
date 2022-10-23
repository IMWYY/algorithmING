#include <vector>

int64_t costOf(std::vector<int>& nums, std::vector<int>& cost, int x) {
	int64_t res = 0;
	for (int i=0; i<nums.size(); ++i) {
		res += int64_t(std::abs(nums[i] - x)) * int64_t(cost[i]); 
	}
	return res;
}

long long minCost(std::vector<int>& nums, std::vector<int>& cost) {
	int s = INT_MAX, e = 0;
	for (int i=0; i<nums.size(); ++i) {
		e = std::max(e, nums[i]);
		s = std::min(s, nums[i]);
	}
	int64_t res = costOf(nums, cost, e);

	while (s < e) {
		int m = (s + e) / 2;
		int64_t l = costOf(nums, cost, m);
		int64_t r = costOf(nums, cost, m+1);
		if (l < r) {
			e = m;
			res = std::min(l, res);
		} else {
			s = m + 1;
			res = std::min(r, res);
		}
	}

	return res;
}
