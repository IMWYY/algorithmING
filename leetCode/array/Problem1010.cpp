
#include <vector>

using namespace std;

/**
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds
 * is divisible by 60.  Formally, we want the number of indices i < j with
 * (time[i] + time[j]) % 60 == 0.
 *
 * Example 1:
 *
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 */

int numPairsDivisibleBy60(vector<int>& time) {
  vector<int> reminders(60, 0);
  for (auto& t : time) {
    reminders[t % 60]++;
  }

  int res = 0;
  res += reminders[0] * (reminders[0] - 1) / 2;
  res += reminders[30] * (reminders[30] - 1) / 2;
  for (size_t i = 1; i < 30; ++i) {
    res += reminders[i] * reminders[60 - i];
  }
  return res;
}