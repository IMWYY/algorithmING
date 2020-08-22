#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

/**
 * Given an integer array arr of distinct integers and an integer k.
 *
 * A game will be played between the first two elements of the array (i.e.
 * arr[0] and arr[1]). In each round of the game, we compare arr[0] with arr[1],
 * the larger integer wins and remains at position 0 and the smaller integer
 * moves to the end of the array. The game ends when an integer wins k
 * consecutive rounds.
 *
 * Return the integer which will win the game.
 *
 * It is guaranteed that there will be a winner of the game.
 */

int getWinner(std::vector<int>& arr, int k) {
  int winner = arr[0], cnt = 0;
  // if k > arr.size(), then the winner is the max element, which is also
  // calculated in the following loop
  for (int i = 1; i < arr.size(); ++i) {
    if (winner < arr[i]) {
      winner = arr[i];
      cnt = 1;
    } else {
      cnt++;
    }
    if (cnt >= k) break;
  }
  return winner;
}
