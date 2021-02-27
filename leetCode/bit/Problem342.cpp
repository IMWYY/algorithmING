
bool isPowerOfFour(int n) {
  if (n < 1 || n & (n - 1)) return false;
  return n & 0x55555555;
}