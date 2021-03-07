#include <iostream>

// gcd(a, b) = gcd(b, a mod b)
int gcd_v1(int a, int b) {
  if (b == 0) return a;
  return gcd_v1(b, a % b);
}

int gcd_v2(int a, int b) {
  if (a == b)
    return a;
  else if (a > b)
    return gcd_v2(a - b, b);
  else
    return gcd_v2(a, b - a);
}

int main() {
  std::cout << gcd_v1(120, 280) << std::endl;
  std::cout << gcd_v2(120, 280) << std::endl;
}