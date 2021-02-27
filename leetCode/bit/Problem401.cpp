#include <bitset>
#include <string>
#include <vector>

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs
 * on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 *
 * Given a non-negative integer n which represents the number of LEDs that are currently on,
 * return all possible times the watch could represent.
 */

int num_of_bits(int);
std::vector<std::string> readBinaryWatch(int num) {
  std::vector<std::string> res;
  if (num == 0) {
    res.push_back("0:00");
    return res;
  }
  std::vector<int> hour;
  for (int i = 0; i < 4 && i <= num; ++i) {
    hour.clear();
    for (int j = 0; j < 12; ++j) {
      if (num_of_bits(j) == i) hour.push_back(j);
    }
    for (int k = 0; k < 60; ++k) {
      if (num_of_bits(k) == num - i) {
        for (int l = 0; l < hour.size(); ++l) {
          res.push_back(std::to_string(hour[l]) + (k < 10 ? ":0" : ":") + std::to_string(k));
        }
      }
    }
  }
  return res;
}

int num_of_bits(int n) {
  int num = 0;
  while (n > 0) {
    if ((n & 1) == 1) num++;
    n >>= 1;
  }
  return num;
}