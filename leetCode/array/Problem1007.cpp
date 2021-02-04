#include <vector>
#include <iostream>

using namespace std;
/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
 * (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * If it cannot be done, return -1.
 *
 * Example 1:
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2,
 * as indicated by the second figure.
 */

int minDominoRotations(vector<int> &A, vector<int> &B) {
    int pivot = 0, count = 0;
    for (int &j : A) {
        if (count == 0) pivot = j;
        if (pivot == j) count++;
        else count--;
    }
    for (int &j : B) {
        if (count == 0) pivot = j;
        if (pivot == j) count++;
        else count--;
    }
    count = 0;
    for (int i=0; i< A.size(); ++i) {
        if (A[i] == pivot) count++;
        if (B[i] == pivot) count++;
    }
    if (count < A.size()) return -1;
    int resA = 0, resB = 0;
    for (int j = 0; j < A.size(); ++j) {
        if (A[j] != pivot && B[j] != pivot) return -1;
        if (A[j] != pivot) resA++;
        if (B[j] != pivot) resB++;

    }
    return resA > resB ? resB : resA;
}

int main() {
    vector<int> A{2, 1, 2, 4, 2, 2}, B{5, 2, 6, 2, 3, 2};
    cout << minDominoRotations(A, B);
}