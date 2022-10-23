#include <vector>

long long makeSimilar(std::vector<int>& nums, std::vector<int>& target) {
	std::sort(nums.begin(), nums.end());
	std::sort(target.begin(), target.end());
	std::vector<int> odd_nums, even_nums, odd_tar, even_tar;
	for (int& x: nums) {
		if (x % 2) odd_nums.push_back(x);
		else even_nums.push_back(x);
	}
	for (int& x: target) {
		if (x % 2) odd_tar.push_back(x);
		else even_tar.push_back(x);
	}
	long long ans = 0;
	for(int i = 0; i < odd_nums.size(); i++){
		if(odd_nums[i] > odd_tar[i]) ans += (odd_nums[i] - odd_tar[i]) / 2;
	}
	for(int i = 0; i < even_nums.size(); i++){
		if(even_nums[i] > even_tar[i]) ans += (even_nums[i] - even_tar[i]) / 2;
	}
	return ans;
}

