#include <iostream>

int main() {
  int local = 2;
  auto adder1 = [](int a, int b) { return a * b; };
  auto adder2 = [](int a, int b)->int { return a * b; };
  auto adder3 = [local](auto a, auto b){ return a * b + local; };
  auto adder4 = [&](auto a, auto b){ return a * b + local; };
  std::cout << adder1(1, 2) << std::endl;
  std::cout << adder2(1.11, 2.2) << std::endl;
  std::cout << adder3(1.11, 2.2) << std::endl;
  std::cout << adder4(1.11, 3.3) << std::endl;
  local = 3;
  std::cout << adder3(1.11, 2.2) << std::endl;
  std::cout << adder4(1.11, 3.3) << std::endl;
}