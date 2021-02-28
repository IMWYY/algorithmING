#include <iostream>

#define COUT_VAR(v) std::cout << #v << ": " << v << std::endl;

class Base {
 public:
  Base(int i) : x(i) {}
  virtual void vprint() { std::cout << "this is base " << x << std::endl; }
  int x;
};

class Child : public Base {
 public:
  Child(int i) : Base(i) {}

  void vprint() { std::cout << "this is child " << x << std::endl; }
};

int main() {
  Base* a = new Child(1);
  Child* b = new Child(2);
  a->vprint();                              // this is child
  b->vprint();                              // this is child
  COUT_VAR(typeid(5).name())                // int
  COUT_VAR((typeid(5) == typeid(int)));     // true
  COUT_VAR(typeid(a).name());               // base*
  COUT_VAR(typeid(b).name());               // child*
  COUT_VAR(typeid(*a).name());              // child
  COUT_VAR((typeid(*a) == typeid(Child)));  // true
  COUT_VAR(typeid(*b).name());              // child
  return 0;
}
