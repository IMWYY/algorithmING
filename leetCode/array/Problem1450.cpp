#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Given two integer arrays startTime and endTime and given an integer
 * queryTime.
 *
 * The ith student started doing their homework at the time startTime[i] and
 * finished it at time endTime[i].
 *
 * Return the number of students doing their homework at time queryTime. More
 * formally, return the number of students where queryTime lays in the interval
 * [startTime[i], endTime[i]] inclusive.
 */

int busyStudent(vector<int> &startTime, vector<int> &endTime, int queryTime) {
  int ret = 0;
  for (int i = 0; i < startTime.size(); ++i)
    if (startTime[i] <= queryTime && endTime[i] >= queryTime) ret++;
  return ret;
}