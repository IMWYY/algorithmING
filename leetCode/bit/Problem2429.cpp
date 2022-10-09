
int numOfOne(int num) {
  int res = 0;
  while (num) {
    res += (num & 1);
    num >>= 1;
  }
  return res;
}

int minimizeXor(int num1, int num2) {
  int one1 = numOfOne(num1);
  int one2 = numOfOne(num2);

  if (one1 == one2)
    return num1;
  else if (one1 > one2) {
    int mask = 1;
    int remain = one1 - one2;
    while (remain) {
      if (num1 & mask) remain--;
      mask <<= 1;
    }
    return (num1 & (~(mask - 1)));
  } else {
    int mask = 1;
    int remain = one2 - one1;
    while (remain) {
      if (!(num1 & mask)) remain--;
      mask <<= 1;
    }
    return (num1 | (mask - 1));
  }
}