//
// Created by 王友运 on 2019-03-07.
//
#include <vector>
#include <string>
#include <bitset>

using namespace std;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs
 * on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 *
 * Given a non-negative integer n which represents the number of LEDs that are currently on,
 * return all possible times the watch could represent.
 *
 * Example:
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 */


int num_of_bits(int n) {
    int num = 0;
    while (n > 0) {
        if ((n & 1) == 1) num++;
        n >>= 1;
    }
    return num;
}

/**
 * 不去排列组合 而是判断数字的1的个数是否满足条件
 */
vector<string> readBinaryWatch(int num) {
    vector<string> res;
    if (num == 0) {
        res.push_back("0:00");
        return res;
    }
    vector<int> hour;
    for (int i = 0; i < 4 && i <= num; ++i) {
        hour.clear();
        for (int j = 0; j < 12; ++j) {
            if (num_of_bits(j) == i) hour.push_back(j);
        }
        for (int k = 0; k < 60; ++k) {
            if (num_of_bits(k) == num - i) {
                for (int l = 0; l < hour.size(); ++l) {
                    res.push_back(to_string(hour[l]) + (k < 10 ? ":0" : ":") + to_string(k));
                }
            }
        }
    }
    return res;
}

/**
 * 更简单粗暴 所有时间组合比较 是否满足1的个数
 */
vector<string> readBinaryWatch1(int num) {
    vector<string> rs;
    for (int h = 0; h < 12; h++)
        for (int m = 0; m < 60; m++)
            if (bitset<10>(h << 6 | m).count() == num)
                rs.emplace_back(to_string(h) + (m < 10 ? ":0" : ":") + to_string(m));
    return rs;
}
