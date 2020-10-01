#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * You are the operator of a Centennial Wheel that has four gondolas, and each
 * gondola has room for up to four people. You have the ability to rotate the
 * gondolas counterclockwise, which costs you runningCost dollars.
 *
 * You are given an array customers of length n where customers[i] is the number
 * of new customers arriving just before the ith rotation (0-indexed). This
 * means you must rotate the wheel i times before the customers[i] customers
 * arrive. You cannot make customers wait if there is room in the gondola. Each
 * customer pays boardingCost dollars when they board on the gondola closest to
 * the ground and will exit once that gondola reaches the ground again.
 *
 * You can stop the wheel at any time, including before serving all customers.
 * If you decide to stop serving customers, all subsequent rotations are free in
 * order to get all the customers down safely. Note that if there are currently
 * more than four customers waiting at the wheel, only four will board the
 * gondola, and the rest will wait for the next rotation.
 *
 * Return the minimum number of rotations you need to perform to maximize your
 * profit. If there is no scenario where the profit is positive, return -1.
 */

int minOperationsMaxProfit(std::vector<int>& customers, int boardingCost,
                           int runningCost) {
  int profit = 0, waiting = 0;
  int max_profit = INT_MIN, max_i = 0;
  for (int i = 0; i < customers.size() || waiting > 0; ++i) {
    if (i < customers.size()) waiting += customers[i];
    int aboard = std::min(waiting, 4);
    waiting -= aboard;
    profit += (aboard * boardingCost - runningCost);
    // std::cout << profit << std::endl;
    if (profit > max_profit) {
      max_profit = profit;
      max_i = i;
    }
  }
  if (max_profit <= 0) return -1;
  return max_i + 1;
}