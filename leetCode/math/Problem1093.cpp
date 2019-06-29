#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * We sampled integers between 0 and 255, and stored the results in an array
 * count:  count[k] is the number of integers we sampled equal to k.
 *
 * Return the minimum, maximum, mean, median, and mode of the sample
 * respectively, as an array of floating point numbers.  The mode is guaranteed
 * to be unique.
 *
 * (Recall that the median of a sample is:
 *
 * The middle element, if the elements of the sample were sorted and the number
 * of elements is odd; The average of the middle two elements, if the elements
 * of the sample were sorted and the number of elements is even.)
 */

// NOTE the way to calculate the middle value: we keep the lv and rv, lv = rv
// when len is even
vector<double> sampleStats(vector<int>& count) {
  double max = 0, min = -1, mean = 0, mode = 0;
  size_t len = 0, cur_len = 0;
  for (size_t i = 0; i < count.size(); ++i) {
    if (count[i] > 0) len += count[i];
  }
  double lv = -1, rv = -1;
  for (size_t i = 0; i < count.size(); ++i) {
    if (count[i] > 0) {
      max = i;
      if (min < 0) min = i;
      mean += count[i] * i;
      if (count[i] > count[mode]) mode = i;
      cur_len += count[i];
      if (lv < 0 && cur_len >= len / 2) lv = i;
      if (rv < 0 && cur_len >= len / 2 + 1) rv = i;
    }
  }
  vector<double> res = {min, max, mean / len, (lv + rv) / 2, mode};
  return res;
}