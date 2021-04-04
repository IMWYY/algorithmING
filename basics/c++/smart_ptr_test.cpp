#include <assert.h>

#include <iostream>
#include <memory>

#define COUT_VAR(v) std::cout << #v << ": " << v << std::endl;

class Base {
 public:
  int x;
  Base() : x(0) { std::cout << "Construct Base 1" << std::endl; }
  Base(int i) : x(i) { std::cout << "Construct Base 2" << std::endl; }
  ~Base() { std::cout << "Delete Base with id " << x << std::endl; }
};

int main() {
  {
    std::unique_ptr<int> p1 = std::make_unique<int>(20);
    std::unique_ptr<int> p2 = std::move(p1);
    // std::unique_ptr<int> p3 = p2; // cannot compile
    assert(p1 == nullptr);
    COUT_VAR(*p2)
    int* raw_p = p2.release();
    assert(p2 == nullptr);
    COUT_VAR(*raw_p);
  }

  std::cout << "==================" << std::endl;

  {
    std::unique_ptr<Base> p5 = std::make_unique<Base>(3);
    COUT_VAR((*p5).x)
    p5.reset(new Base(333));  // the old one is deleted
  }

  std::cout << "==================" << std::endl;

  {
    // std::unique_ptr<Base[]> p6(new Base[3]);
    std::unique_ptr<Base[]> p6 = std::make_unique<Base[]>(3);
    p6[0] = 0;
    p6[1] = 1;
    p6[2] = 2;
    COUT_VAR(p6[0].x)
  }

  std::cout << "==================" << std::endl;

  std::shared_ptr<Base[]> p3(new Base[3]{1, 2, 3},
                             [](Base* p) -> void { delete[] p; });
  // std::shared_ptr<Base[]> p3(new Base[3]{1, 2, 3});
  // std::shared_ptr<Base[]> p3 = std::make_shared<Base[]>(3);
  COUT_VAR(p3[0].x);
  COUT_VAR(p3[2].x);
  COUT_VAR(p3.use_count());
  {
    std::shared_ptr<Base[]> p4 = p3;
    COUT_VAR(p4.use_count());
  }
  COUT_VAR(p3.use_count());

  {
    // NEW SOLUTION: check expired() or lock() to determine if pointer is valid
    // takes ownership of pointer
    std::shared_ptr<int> sptr;
    sptr.reset(new int);
    *sptr = 10;

    // get pointer to data without taking ownership
    COUT_VAR(sptr.use_count());
    std::weak_ptr<int> weak1 = sptr;
    COUT_VAR(sptr.use_count()); // the use_count will not increase

    // deletes managed object, acquires new pointer
    sptr.reset(new int);  // this will invalidate weak ptr
    *sptr = 5;

    // get pointer to new data without taking ownership
    std::weak_ptr<int> weak2 = sptr;

    // weak1 is expired!
    if (auto tmp = weak1.lock())
      std::cout << *tmp << '\n';
    else
      std::cout << "weak1 is expired\n";

    // weak2 points to new data (5)
    if (auto tmp = weak2.lock())
      std::cout << *tmp << '\n';
    else
      std::cout << "weak2 is expired\n";
  }
}