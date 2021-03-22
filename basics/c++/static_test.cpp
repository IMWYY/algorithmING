#include <iostream>

std::pair<int, int*> function(int a) {
  static int cnt = 0;
  cnt++;
  if (a > 0) {
    return {a * 100, &cnt};
  } else {
    return function(a + 1);
  }
}

int main() {
  auto p = function(-2);
  std::cout << p.first << std::endl;
  std::cout << *p.second << std::endl;
  p.second = 0;
  std::cout << *p.second << std::endl;  // segmentation fault
}