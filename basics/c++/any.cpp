
#include <assert.h>

#include <memory>
#include <type_traits>

class Any {
 public:
  template <class T>
  friend T any_cast(Any&);

  template <class T>
  friend T any_cast(const Any&);

  template <class T>
  friend T* any_cast(Any*);

  template <class T>
  friend const T* any_cast(const Any*);

  Any() : ptr(nullptr) {}

  Any(Any&& x) : ptr(std::move(x.ptr)) {}

  Any(const Any& x) {
    if (x.ptr) ptr = x.ptr->clone();
  }

  Any& operator=(Any&& x) {
    ptr = std::move(x.ptr);
    return *this;
  }

  Any& operator=(const Any& x) {
    if (x.ptr) ptr = x.ptr->clone();
    return *this;
  }

  template <typename T>
  Any& operator=(T&& x) {
    ptr.reset(std::unique_ptr<typename std::decay<T>::type>(x));
    return *this;
  }

  template <typename T>
  Any& operator=(const T& x) {
    ptr.reset(std::unique_ptr<typename std::decay<T>::type>(x));
    return *this;
  }

  template <typename T>
  Any(const T& t) : ptr(std::unique_ptr<typename std::decay<T>::type>(t)) {}

  struct Holder {
    virtual std::unique_ptr<Holder> clone() = 0;
  };

  template <typename T>
  struct ConcreteHolder : public Holder {
    ConcreteHolder(T&& t) : data(std::move(t)) {}
    ConcreteHolder(const T& t) : data(t) {}
    std::unique_ptr<Holder> clone() override {
      return std::unique_ptr<Holder>(new ConcreteHolder<T>(data));
    }

    T data;
  };

 private:
  std::unique_ptr<Holder> ptr;
};

template <class T>
T any_cast(any& x) {
  if (x.ptr->type() != typeid(T)) assert(false);
  return static_cast<any::concrete<T>*>(x.ptr.get())->data;
}

template <class T>
T any_cast(const any& x) {
  return any_cast<T>(any(x));
}

template <class T>
T* any_cast(any* x) {
  return dynamic_cast<T*>(x->ptr.get());
}

template <class T>
const T* any_cast(const any* x) {
  return dynamic_cast<const T*>(x->ptr.get());
}
