
/**
 * Given a positive integer K, you need find the smallest positive integer N
 * such that N is divisible by K, and N only contains the digit 1.
 * Return the length of N.  If there is no such N, return -1.
 * */

int smallestRepunitDivByK(int K) {
  if ((K & 1) == 0) return -1;
  while (K ^ (~1) != 0) {
    K += K;
  }
  return K;
}