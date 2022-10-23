#include <vector>

int gcd(int a, int b) {
	if (b == 0) return a;
	return gcd(b, a % b);
}

int subarrayGCD(std::vector<int>& nums, int k) {
	int res = 0;
	for (int i =0; i<nums.size(); ++i) {
		int g = nums[i];
		for (int j=i; j<nums.size(); ++j) {
			g = gcd(g, nums[j]);
			if (g < k) break;
			if (g == k) res ++;
		}
	}
	return res;
}
