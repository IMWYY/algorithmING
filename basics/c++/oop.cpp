#include <stdlib.h>

#include <iostream>
class Base {
 public:
  Base() : v1(rand()) { std::cout << "Construct Base" << std::endl; }
  virtual ~Base() { std::cout << "Deconstruct Base" << std::endl; }
  void f1(int i) { std::cout << "Base f1(" << i << "). " << std::endl; }
  virtual void f2(int i) { std::cout << "Base f2(" << i << "). " << std::endl; }

  bool operator<(const Base& other) const {
    std::cout << "Base < " << v1 << std::endl;
    return v1 < other.v1;
  }

  Base& operator++() {
    std::cout << "prefix ++" << std::endl;
    ++v1;
    return *this;
  }

  Base operator++(int) {
    std::cout << "suffix ++" << std::endl;
    Base tmp = *this;
    v1++;
    return tmp;
  }

 private:
  int v1;
};

class Derived : public Base {
 public:
  Derived() : v2(rand()) { std::cout << "Construct Derived" << std::endl; }
  virtual ~Derived() { std::cout << "Deconstruct Derived" << std::endl; }
  // non-virtual function cannot be override
  void f1(int i) { std::cout << "Derived f1(" << i << "). " << std::endl; }
  void f2(int i) { std::cout << "Derived f2(" << i << "). " << std::endl; }

  //  operator cannot be override
  bool operator<(const Derived& other) const {
    std::cout << "Derived < " << v2 << std::endl;
    return v2 < other.v2;
  }

 private:
  int v2;
};

int main() {
  Base* b1 = new Base;
  Base* b2 = new Derived;
  Derived* b3 = dynamic_cast<Derived*>(b2);
  Derived& b4 = dynamic_cast<Derived&>(*b2);
  Derived* b5 = new Derived;

  std::cout << "====================" << std::endl;
  b1->f1(3);  // base
  b2->f1(3);  // base
  b3->f1(3);  // derived
  b4.f1(3);   // derived

  std::cout << "====================" << std::endl;
  b1->f2(3);  // base
  b2->f2(3);  // derived
  b3->f2(3);  // derived
  b4.f2(3);   // derived

  std::cout << "====================" << std::endl;
  std::cout << (*b1 < *b2) << std::endl;  // call Base <
  std::cout << (*b1 < *b5) << std::endl;  // call Base <
  std::cout << (*b3 < *b5) << std::endl;  // call Derived <

  std::cout << "====================" << std::endl;
  b1->operator++();
  // ++b1;
  std::cout << "====================" << std::endl;
  b1->operator++(1);
  // b1++;

  delete b1;
  std::cout << "====================" << std::endl;
  delete b2;
  std::cout << "====================" << std::endl;
  delete b5;
}
