
// solution1: simulate the process of addtion, bit by bit
int getSum(int a, int b) {
  int res = 0, bit = 0, carry = 0;
  while (bit < 32) {
    if ((a ^ b ^ carry) & 1) {
      res |= (1 << bit);
    }
    bit++;
    carry = (a & b & 1) || (b & carry & 1) || (a & carry & 1);
    a >>= 1;
    b >>= 1;
  }
  return res;
}

// solution2:
// If there is no carry, then res=a^b
// To handle the carry, we use a&b to get the carry, left shift it and add it to the result
// repeat the process util there is no carry.
int getSum(int a, int b) {
  if (a == 0)
    return b;
  while (b != 0) {
    int carry = a & b;
    a = a ^ b;
    b = carry << 1;
  }
  return a;
}