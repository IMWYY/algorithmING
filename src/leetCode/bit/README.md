# 位操作

一篇关于位操作总结的文章，参考[这里](https://leetcode.com/problems/sum-of-two-integers/discuss/84278/A-summary:-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently)

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

