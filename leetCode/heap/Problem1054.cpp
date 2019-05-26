
#include <algorithm>
#include <unordered_map>
#include <vector>
using namespace std;

/**
 * Distant Barcodes
 * In a warehouse, there is a row of barcodes, where the i-th barcode is
 * barcodes[i].
 * Rearrange the barcodes so that no two adjacent barcodes are equal.  You may
 * return any answer, and it is guaranteed an answer exists.
 */

struct Key {
  int code;
  int count;

  Key(int co, int cnt) : code(co), count(cnt) {}

  friend bool operator<(const Key &l, const Key &r) {
    return l.count < r.count;
  }
};

/**
 * stat the count of every barcode, then put the barcode 
 * with the max count with one gap, and so on.
 */
vector<int> rearrangeBarcodes(vector<int> &barcodes) {
  unordered_map<int, int> bucket;
  for (int &a : barcodes) {
    if (bucket.count(a) != 0) {
      bucket[a] = bucket[a] + 1;
    } else {
      bucket[a] = 1;
    }
  }

  vector<Key> data;
  data.reserve(10000);
  for (auto iter = bucket.begin(); iter != bucket.end(); iter++) {
    data.emplace_back(iter->first, iter->second);
  }
  make_heap(data.begin(), data.end());

  vector<int> res(barcodes.size());
  int cnt = 0, index = 0;
  while (cnt < res.size()) {
    Key max_k = data.front();
    pop_heap(data.begin(), data.end());
    data.pop_back();
    while (max_k.count > 0) {
      res[index] = max_k.code;
      index += 2;  // with one gap
      if (index >= res.size()) index = 1;
      cnt++;
      max_k.count--;
    }
  }
  return res;
}