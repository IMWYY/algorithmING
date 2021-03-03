#include <assert.h>

#include <iostream>
#include <memory>

#define COUT_VAR(v) std::cout << #v << ": " << v << std::endl;

class Base {
 public:
  int x;
  Base(int i) : x(i) { std::cout << "Construct Base" << std::endl; }
  ~Base() { std::cout << "Delete Base" << std::endl; }
};

int main() {
  std::unique_ptr<int> p1 = std::make_unique<int>(20);
  std::unique_ptr<int> p2 = std::move(p1);
  // std::unique_ptr<int> p3 = p2; // cannot compile
  assert(p1 == nullptr);
  COUT_VAR(*p2)
  int* raw_p = p2.release();
  COUT_VAR(*raw_p);

  std::shared_ptr<Base[]> p3(new Base[3]{1, 2, 3}, [](Base* p) { delete[] p; });
  //   std::shared_ptr<Base[]> p3(new Base[3]{1, 2, 3});
  COUT_VAR(p3[0].x);
  COUT_VAR(p3[2].x);
  COUT_VAR(p3.use_count());
  {
    std::shared_ptr<Base[]> p4 = p3;
    COUT_VAR(p4.use_count());
  }
  COUT_VAR(p3.use_count());
}