#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * You are driving a vehicle that has capacity empty seats initially available
 * for passengers.  The vehicle only drives east (ie. it cannot turn around and
 * drive west.)
 *
 * Given a list of trips, trip[i] = [num_passengers, start_location,
 * end_location] contains information about the i-th trip: the number of
 * passengers that must be picked up, and the locations to pick them up and drop
 * them off.  The locations are given as the number of kilometers due east from
 * your vehicle's initial location.
 *
 * Return true if and only if it is possible to pick up and drop off all
 * passengers for all the given trips.
 *
 * trips.length <= 1000
 * trips[i].length == 3
 * 1 <= trips[i][0] <= 100
 * 0 <= trips[i][1] < trips[i][2] <= 1000
 * 1 <= capacity <= 100000
 */

// use array of 1000 size to indicate the number of passengers at each
// locations. return false once there exsit one location that the number of
// passengers is greater than capacity
bool carPooling(vector<vector<int>>& trips, int capacity) {
  vector<int> passengers(1000, 0);
  for (vector<int>& t : trips) {
    passengers[t[1]] += t[0];
    passengers[t[2]] -= t[0];
  }
  int num_passengers = 0;
  for (int p : passengers) {
    if (p != 0) {
      num_passengers += p;
      if (num_passengers > capacity) return false;
    }
  }
  return true;
}