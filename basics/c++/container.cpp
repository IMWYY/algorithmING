#include <assert.h>

#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <unordered_map>
#include <vector>

struct Info {
  int v1;
  int v2;
  int v3;
  bool operator<(const Info& other) const {
    return v1 + v2 + v3 < other.v1 + other.v2 + other.v3;
  }
  bool operator>(const Info& other) const {
    return v1 + v2 + v3 > other.v1 + other.v2 + other.v3;
  }
  bool operator==(const Info& other) const {
    return v1 == other.v1 && v2 == other.v2 && v3 == other.v3;
  }
};

struct MyHash {
  int operator()(const Info& info) const {
    return std::hash<int>()(info.v1) ^ std::hash<int>()(info.v2) ^
           std::hash<int>()(info.v3);
  }
};

int main() {
  // pointer of customized struct
  {
    std::vector<Info*> infos;
    Info i1 = {1, 2, 3};
    Info i2 = {0, 2, 3};
    infos.push_back(&i1);
    infos.push_back(&i2);
    std::sort(infos.begin(), infos.end(), [](const Info* p1, const Info* p2) {
      return p1->v1 + p1->v2 + p1->v3 < p2->v1 + p2->v2 + p2->v3;
    });
    assert(infos[0]->v1 == 0);
  }

  // pointer of customized struct
  {
    auto f = [](const Info* p1, const Info* p2) {
      return p1->v1 + p1->v2 + p1->v3 < p2->v1 + p2->v2 + p2->v3;
    };
    std::priority_queue<Info*, std::vector<Info*>, decltype(f)> infos(f);

    Info i1 = {1, 1, 6};
    Info i2 = {0, 0, 0};
    Info i3 = {0, 2, 3};
    Info i4 = {0, 1, 5};
    infos.push(&i1);
    infos.push(&i2);
    infos.push(&i3);
    infos.push(&i4);
    while (!infos.empty()) {
      std::cout << infos.top()->v3 << std::endl;
      infos.pop();
    }
  }

  // priority_queue
  {
    // max heap
    std::priority_queue<Info, std::vector<Info>, std::less<Info>> pq;
    // min heap
    // std::priority_queue<Info, std::vector<Info>, std::greater<Info>> pq;
    pq.push({0, 1, 3});
    pq.push({10, 0, 0});
    pq.push({3, 1, 2});
    std::cout << pq.top().v1 << std::endl;
    assert(pq.top().v1 == 10);
  }
  // unordered_map
  {
    std::unordered_map<Info, int, MyHash> mp;
    mp[{0, 1, 3}] = 2;
    mp[{0, 1, 3}] += 2;
    mp[{4, 1, 3}] = 3;
    int v = mp[{0, 1, 3}];
    assert(v == 4);
    v = mp[{4, 1, 3}];
    assert(v == 3);
  }

  {
    auto h = [](const Info& info) {
      return std::hash<int>()(info.v1) ^ std::hash<int>()(info.v2) ^
             std::hash<int>()(info.v3);
    };
    std::unordered_map<Info, int, decltype(h)> mp(10, h);
    mp[{0, 1, 3}] = 2;
    mp[{0, 1, 3}] += 2;
    mp[{4, 1, 3}] = 3;
    int v = mp[{0, 1, 3}];
    assert(v == 4);
    v = mp[{4, 1, 3}];
    assert(v == 3);
  }

  // map
  {
    std::map<Info, int, std::less<Info>> mp;
    mp[{1, 2, 4}] = 1;
    mp[{1, 4, 4}] = 2;
    mp[{1, 6, 4}] = 3;
    mp[{1, 8, 4}] = 4;
    typename std::map<Info, int, std::less<Info>>::iterator it =
        mp.find({1, 8, 4});
    // auto it = mp.find("ccd");
    assert(it != mp.end());
    assert(it->second == 4);
  }

  // multimap
  {
    std::multimap<Info, int, std::less<Info>> mp;
    mp.insert({{1, 2, 4}, 5});
    mp.insert({{1, 2, 4}, 2});
    mp.insert({{1, 6, 4}, 3});
    mp.insert({{1, 2, 4}, 4});
    typename std::multimap<Info, int, std::less<Info>>::iterator it =
        mp.find({1, 2, 4});
    // auto it = mp.find("ccd");
    assert(it != mp.end());
    Info info = it->first;
    assert(it->first == info);
    assert(it->second == 5);
    it++;
    assert(it->first == info);
    assert(it->second == 2);
    it++;
    assert(it->first == info);
    assert(it->second == 4);
    it++;
    assert(it->second == 3);
  }

  // make heap
  {
    std::cout << "================================" << std::endl;
    // c++ make heap
    std::vector<int> arr = {0, 4, 6, 9, 3, 3, 10, 73, 5};
    while (!arr.empty()) {
      std::make_heap(arr.begin(), arr.end(), std::greater<>());
      std::cout << arr.front() << ", ";
      std::pop_heap(arr.begin(), arr.end(), std::greater<>());
      arr.pop_back();
    }
    std::cout << std::endl;

    std::cout << "================================" << std::endl;
    arr = {0, 4, 6, 9, 3, 3, 10, 73, 5};
    while (!arr.empty()) {
      std::make_heap(arr.begin(), arr.end());
      std::cout << arr.front() << ", ";
      std::pop_heap(arr.begin(), arr.end());
      arr.pop_back();
    }
    std::cout << std::endl;
  }
}