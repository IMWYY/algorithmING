
/**
 * Given a positive integer K, you need find the smallest positive integer N
 * such that N is divisible by K, and N only contains the digit 1.
 * Return the length of N.  If there is no such N, return -1.
 * */

// an excellent explanation
// https://leetcode.com/problems/smallest-integer-divisible-by-k/discuss/260916/Proof%3A-we-only-need-to-consider-the-first-K-candidates-in-1-11-111-1111-...
int smallestRepunitDivByK(int K) {
  if (K % 2 == 0 || K % 5 == 0) return -1;
  int num = 0;
  for (int i=1; i<= K; ++i) {
    num = (num * 10 + 1) % K;
    if (num % K == 0) return i;
  }
  return -1;
}