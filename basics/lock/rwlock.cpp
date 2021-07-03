#include <chrono>
#include <condition_variable>
#include <iostream>
#include <mutex>
#include <thread>

class RWLock {
 public:
  void read_lock() {
    std::unique_lock<std::mutex> ul(m);
    while (writer) {
      cv.wait(ul);
    }
    readers++;
  }

  void read_unlock() {
    std::unique_lock<std::mutex> ul(m);
    readers--;
    if (readers == 0) cv.notify_all();
  }

  void write_lock() {
    std::unique_lock<std::mutex> ul(m);
    while (readers > 0 || writer) {
      cv.wait(ul);
    }
    writer = true;
  }

  void write_unlock() {
    std::unique_lock<std::mutex> ul(m);
    writer = false;
    cv.notify_all();
  }

 private:
  int readers = 0;
  bool writer = false;
  std::mutex m;
  std::condition_variable cv;
};

int counter = 0;
RWLock rwl;

int worker(int id, bool read) {
  if (read) {
    for (int i = 0; i < 10; ++i) {
      rwl.read_lock();
      std::cout << id << ": counter=" << counter << std::endl;
      rwl.read_unlock();
      std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    }
  } else {
    for (int i = 0; i < 10; ++i) {
      rwl.write_lock();
      counter++;
      std::cout << id << ": add counter " << counter << std::endl;
      rwl.write_unlock();
      std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    }
  }
  return 0;
}

int main() {
  std::thread t1(worker, 0, true);
  std::thread t2(worker, 1, true);
  std::thread t3(worker, 2, false);
  std::thread t4(worker, 3, false);
  std::thread t5(worker, 4, false);
  t1.join();
  t2.join();
  t3.join();
  t4.join();
  t5.join();
}