#include <iostream>

int main() {
  auto adder1 = [](int a, int b) { return a * b; };
  auto adder2 = [](int a, int b)->int { return a * b; };
  auto adder3 = [](auto a, auto b){ return a * b; };
  std::cout << adder1(1, 2) << std::endl;
  std::cout << adder2(1.11, 2.2) << std::endl;
  std::cout << adder3(1.11, 2.2) << std::endl;
}