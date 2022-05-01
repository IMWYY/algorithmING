#include <iostream>

namespace st {
template <typename Result, typename... Args>
struct abstract_function {
  virtual Result operator()(Args... args) = 0;
  virtual abstract_function *clone() const = 0;
  virtual ~abstract_function() = default;
};

template <typename Func, typename Result, typename... Args>
class concrete_function : public abstract_function<Result, Args...> {
  Func f;

 public:
  concrete_function(const Func &x) : f(x) {}
  Result operator()(Args... args) override { return f(args...); }
  concrete_function *clone() const override { return new concrete_function(f); }
};

template <typename Func>
struct func_filter {
  typedef Func type;
};
// class specialization
template <typename Result, typename... Args>
struct func_filter<Result(Args...)> {
  typedef Result (*type)(Args...);
};

template <typename signature>
class function;

template <typename Result, typename... Args>
class function<Result(Args...)> {
  abstract_function<Result, Args...> *f;

 public:
  function() : f(nullptr) {}
  template <typename Func>
  function(const Func &x)
      : f(new concrete_function<typename func_filter<Func>::type, Result,
                                Args...>(x)) {}

  function(const function &rhs) : f(rhs.f ? rhs.f->clone() : nullptr) {}

  function &operator=(const function &rhs) {
    if ((&rhs != this) && (rhs.f)) {
      auto *temp = rhs.f->clone();
      delete f;
      f = temp;
    }
    return *this;
  }

  template <typename Func>
  function &operator=(const Func &x) {
    auto *temp = new concrete_function<typename func_filter<Func>::type, Result,
                                       Args...>(x);
    delete f;
    f = temp;
    return *this;
  }

  Result operator()(Args... args) {
    if (f)
      return (*f)(args...);
    else
      return Result();
  }

  ~function() { delete f; }
};
}  // namespace st

// ___________________[ Example of usage ]___________________ //

int func1(double) { return 1; }
struct Functor2 {
  int operator()(double) { return 2; }
};

int main() {
  int res = 10;
  st::function<int(double)> f(func1);

  res -= f(1.0);
  std::cout << res << std::endl;
  // f = Functor2{};
  // res -= f(2.0);
  // std::cout << res << std::endl;

  // auto f3 = [](double) { return 3; };
  // f = f3;
  // res -= f3(0);
  // std::cout << res << std::endl;
}