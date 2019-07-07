
#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * There are n flights, and they are labeled from 1 to n.
 * We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k]
 * means that we booked k seats from flights labeled i to j inclusive.
 * Return an array answer of length n, representing the number of seats booked
 * on each flight in order of their label.
 * 
 * Example 1: 
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5 
 * Output: [10,55,45,25,25]
 */

vector<int> corpFlightBookings(vector<vector<int>>& bookings, int n) {
  std::vector<int> flights(n, 0);
  for (vector<int>& b : bookings) {
    flights[b[0] - 1] += b[2];
    if (b[1] < n) {
      flights[b[1]] -= b[2];
    }
  }
  for (size_t i = 1; i < n; ++i) {
    flights[i] += flights[i - 1];
  }
  return flights;
}