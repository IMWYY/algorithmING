#include <vector>

bool validUtf8(std::vector<int>& data) {
  std::vector<int> b = {0, 0b11000000, 0b11100000, 0b11110000};
  std::vector<int> m = {0b10000000, 0b11100000, 0b11110000, 0b11111000};

  for (int i = 0; i < data.size();) {
    int n = -1;
    for (int j = 0; j < b.size(); ++j) {
      if ((data[i] & m[j]) == b[j]) n = j;
    }
    if (n == -1) return false;
    for (int j = 0; j < n; ++j) {
      if (i + j + 1 >= data.size()) return false;
      if ((data[i + j + 1] & 0b11000000) != 0b10000000) return false;
    }
    i += (n + 1);
  }
  return true;
}