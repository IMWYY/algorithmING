//
// Created by 王友运 on 2019-03-02.
//
#include <vector>
#include <iostream>

using namespace std;

int maxRotateFunction(vector<int> &A) {
    if (A.empty())
        return 0;
    long long sum = 0, f0 = 0;
    for (size_t i = 0; i < A.size(); ++i) {
        sum += A.at(i);
        f0 += i * A.at(i);
    }
    long long res = f0, delta = 0;
    for (size_t i = A.size() - 1; i > 0; i--) {
        delta += sum - A.size() * A.at(i);
        res = max(res, f0 + delta);
    }

    return static_cast<int>(res);
}


int main() {
    vector<int> a{2147483647,2147483647};
    cout << maxRotateFunction(a) << endl;
}
