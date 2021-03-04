#include <iostream>

int fun(int *ptr) {
  *ptr = *ptr + 10;
  return (*ptr);
}

int main(void) {
  const int val = 10;
  const int *ptr = &val;
  int *ptr1 = const_cast<int *>(ptr);
  fun(ptr1);
  std::cout << val << std::endl;  // behaviour is undefined
  return 0;
}