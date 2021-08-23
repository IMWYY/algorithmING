#include <queue>
#include <vector>

class KthLargest {
public:

    KthLargest(int k, vector<int>& nums): k(k) {
        for (auto n : nums) {
            if (min_heap.size() < k) {
                min_heap.push(n);
            } else if (n > min_heap.top()) {
                min_heap.pop();
                min_heap.push(n);
            }
        }
    }

    int add(int val) {
        min_heap.push(val);
        if (min_heap.size() > k) {
            min_heap.pop();
        }
        return min_heap.top();
    }

    int k;
    std::priority_queue<int, std::vector<int>, std::greater<int>> min_heap;
};