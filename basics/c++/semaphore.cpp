#include <assert.h>

#include <condition_variable>
#include <iostream>
#include <mutex>
#include <thread>

class Semaphore {
 public:
  Semaphore(int c) : counter(c) { assert(c >= 0); }

  void acquire() {
    std::unique_lock<std::mutex> ul;
    while (counter == 0) {
      cv.wait(ul);
    }
    counter--;
  }

  void release() {
    std::unique_lock<std::mutex> ul;
    counter++;
    cv.notify_one();
  }

 private:
  std::condition_variable cv;
  std::mutex m;
  int counter;
};