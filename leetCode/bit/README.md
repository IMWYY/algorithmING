# Bit operations

A summary about bit opeartions，see[here](https://leetcode.com/problems/sum-of-two-integers/discuss/84278/A-summary:-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently)


```c++
bool is_2power(int x) {  // check whether the number is power of 2?
  if (x <= 0) return false;
  return (x & (x - 1)) == 0;
}

int low_bit(int x) {
  return x & (-x);
}

int smallest_2power_ge(int x) { // smallest number that is greater than x and is power of 2
  int max = 1 << 30;
  int n = x - 1;
  x |= x >> 1;
  x |= x >> 2;
  x |= x >> 4;
  x |= x >> 8;
  x |= x >> 16;
  return x < 0 ? 1 : (x >= max ? max : x + 1);
}
```

- Set union A | B
- Set intersection A & B
- Set subtraction A & ~B
- Set negation ALL_BITS ^ A or ~A
- Set bit A |= 1 << bit
- Clear bit A &= ~(1 << bit)
- Test bit (A & 1 << bit) != 0
- **Extract last bit A&-A or A&~(A-1) or x^(x&(x-1))**
- **Remove last bit A&(A-1)**
- Get all 1-bits ~0

