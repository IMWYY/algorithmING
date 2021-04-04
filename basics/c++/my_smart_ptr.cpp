#include <iostream>

#define COUT_VAR(v) std::cout << #v << ": " << v << std::endl;

class Counter;

template <typename T>
class my_shared_ptr;

template <typename T>
class my_weak_ptr;

class Counter {
 public:
  Counter() : s(0), w(0) {}
  int s;
  int w;
};

template <typename T>
class my_shared_ptr {
  friend class my_weak_ptr<T>;

 public:
  my_shared_ptr(T* p) : _ptr(p) {
    cnt = new Counter;
    if (p)
      cnt->s = 1;
    else
      cnt->s = 0;
  }

  ~my_shared_ptr() {
    release();
    std::cout << "~my_shared_ptr" << std::endl;
  }

  my_shared_ptr(const my_shared_ptr<T>& other)
      : _ptr(other._ptr), cnt(other.cnt) {
    if (_ptr && cnt) cnt->s++;
  }

  my_shared_ptr(const my_weak_ptr<T>& other)
      : _ptr(other._ptr), cnt(other.cnt) {
    if (_ptr && cnt) cnt->s++;
  }

  my_shared_ptr<T>& operator=(const my_shared_ptr& other) {
    if (this != &other) {
      if (cnt) release();  // release existing data if any
      _ptr = other._ptr;
      cnt = other.cnt;
      cnt->s++;
    }
    return *this;
  }

  int count() {
    if (!cnt) return 0;
    return cnt->s;
  }

  T& operator*() { return *_ptr; }

 private:
  void release() {
    if (!cnt) return;
    cnt->s--;
    if (cnt->s == 0) {
      if (_ptr) {
        delete _ptr;
        _ptr = nullptr;
      }
      if (cnt->w == 0) {
        delete cnt;
        cnt = nullptr;
      }
    }
  }

  T* _ptr;
  Counter* cnt;
};

template <typename T>
class my_weak_ptr {
 public:
  my_weak_ptr() : _ptr(nullptr), cnt(nullptr) {}

  my_weak_ptr(my_shared_ptr<T>& sp) : _ptr(sp._ptr), cnt(sp.cnt) {
    if (cnt) cnt->w++;
  }

  my_weak_ptr(my_weak_ptr<T>& sp) : _ptr(sp._ptr), cnt(sp.cnt) {
    if (cnt) cnt->w++;
  }

  ~my_weak_ptr() {
    std::cout << "~my_weak_ptr" << std::endl;
    release();
  }

  bool expired() {
    if (cnt && cnt->s > 0) return false;
    return true;
  }
  int count() {
    if (!cnt) return 0;
    return cnt->w;
  }

  my_shared_ptr<T> lock() { return my_shared_ptr<T>(*this); }

 private:
  void release() {
    if (!cnt) return;
    cnt->w--;
    if (cnt->s == 0 && cnt->w == 0) {
      delete cnt;
      cnt = nullptr;
    }
  }
  T* _ptr;
  Counter* cnt;
};

int main() {
  {
    my_shared_ptr<int> p1(new int(3));
    COUT_VAR(*p1);
  }
  {
    my_shared_ptr<int> p1(new int(3));
    my_shared_ptr<int> p2(p1);
    COUT_VAR(*p2);
    COUT_VAR(p2.count());
  }

  {
    my_shared_ptr<int> p1(new int(3));
    my_shared_ptr<int> p2(p1);
    my_weak_ptr<int> p3(p2);
    COUT_VAR(p2.count());
    COUT_VAR(p3.count());
  }
}