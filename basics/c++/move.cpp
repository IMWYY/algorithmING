#include <iostream>

void overloaded(int const& arg) { std::cout << "by lvalue\n"; }
void overloaded(int&& arg) { std::cout << "by rvalue\n"; }

template <typename t>
/* "t &&" with "t" being template param is special, and  adjusts "t" to be
   (for example) "int &" or non-ref "int" so std::forward knows what to do. */
void forwarding(t&& arg) {
  std::cout << "via std::forward: ";
  overloaded(std::forward<t>(arg));
  std::cout << "via std::move: ";
  overloaded(std::move(arg));  // conceptually this would invalidate arg
  std::cout << "by simple passing: ";
  overloaded(arg);
}

void f(int&& i) { std::cout << "call f(int&& i)." << std::endl; }

void f(const int& i) { std::cout << "call f(const int& i)." << std::endl; }

void f(int& i) { std::cout << "call f(int& i)." << std::endl; }

int main() {
  std::cout << "****************case 1******************" << std::endl;
  std::cout << "initial caller passes rvalue:" << std::endl;
  forwarding(5);
  // via std::forward: by rvalue
  // via std::move: by rvalue
  // by simple passing: by lvalue
  std::cout << std::endl << "initial caller passes lvalue:" << std::endl;
  int x = 5;
  forwarding(x);
  // via std::forward: by lvalue
  // via std::move: by rvalue
  // by simple passing: by lvalue

  std::cout << "****************case 2******************" << std::endl;
  int&& rr = 5;  // rr is a lvalue reference
  f(rr);         // call f(int&)
  f(x);          // call f(int&)
  f(10);         // call f(int&&)

  std::cout << "****************case 3******************" << std::endl;
  auto&& v1 = rr;  // universal reference
  f(v1);           // call f(int&)
  const auto& v2 = rr;
  f(v2);  // call f(const int&)
  auto&& v3 = 10;
  f(v3);  // call f(int&)
}