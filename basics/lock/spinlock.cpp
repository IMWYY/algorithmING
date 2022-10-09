
template <typename T>
class SharedPtr;

template <typename T>
class WeakPtr;

class Counter {
 public:
  int s;
  int w;
  Counter() : s(0), w(0) {}
}

template <typname T>
class SharedPtr {
 public:
  friend class WeakPtr<T>;
  SharedPtr(T* p) : ptr(p) {
    cnt = new Counter();
    cnt->s++;
  }
  ~SharedPtr() { release(); }

  SharedPtr(const SharedPtr<T>& other) {
    this->ptr = other.ptr;
    this->cnt = other.cnt;
    if (this->cnt && this->ptr) {
      this->cnt->s++;
    }
  }

  SharedPtr(const WeakPtr<T>& other) {
    this->ptr = other._ptr;
    this->cnt = other._cnt;
    if (this->cnt && this->ptr) {
      this->cnt->s++;
    }
  }

  SharedPtr<T>& operator=(const SharedPtr<T>& other) {
    if (this == &other) return *this;
    release();
    this->cnt = other.cnt;
    this->ptr = other.ptr;
    if (this->cnt && this->ptr) {
      this->cnt->s++;
    }
    return *this;
  }

  T* get() { return ptr; }

  T& operator*() { return *ptr; }

 private:
  void release() {
    if (this->cnt) {
      cnt->s--;
      if (cnt->s == 0) {
        if (ptr) {
          delete ptr;
          ptr = nullptr;
        }
        if (cnt->w == 0) {
          delete cnt;
          cnt = nullptr;
        }
      }
    }
  }

  T* ptr;
  Counter* cnt;
}

template <typname T>
class WeakPtr {
 public:
  WeakPtr(T* t) : _ptr(t) {
    cnt = new Counter();
    if (_ptr) {
      _cnt->w++;
    }
  }
  ~WeakPtr() { release(); }

  std::SharedPtr<T> lock() const {
    if (_cnt && _cnt->s > 0) {
      return _ptr
    }
  }
  bool expired() const {
    if (_cnt && _ptr) {
      return _cnt->s <= 0;
    }
    return true;
  }

 private:
  void release() {
    if (_cnt) {
      _cnt->w--;
      if (_cnt->w == 0 && _cnt->s == 0) {
        delete _cnt;
        _cnt = nullptr;
      }
    }
  }
  T* _ptr;
  Count* _cnt;
}