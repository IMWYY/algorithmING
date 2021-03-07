#include <iostream>
#include <string>
#include <vector>

std::vector<std::string> split(std::string s, std::string seperator) {
  std::vector<std::string> res;
  size_t start = 0;
  size_t end = s.find(seperator, start);
  while (end != std::string::npos) {
    // if (end > start) res.push_back(s.substr(start, end - start));
    res.push_back(s.substr(start, end - start));
    start = end + seperator.size();
    end = s.find(seperator, start);
  }
  if (s.size() > start) res.push_back(s.substr(start, end - start));
  return res;
}

int main() {
  auto res = split("1::2::3::4:2134::123124124", "::");
  for (auto s : res) {
    std::cout << s << std::endl;
  }
}