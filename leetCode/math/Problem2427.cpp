
int gcd(int a, int b) {
  if (b == 0) return a;
  return gcd(b, a % b);
}

int commonFactors(int a, int b) {
  int g = gcd(a, b);
  int res = 0;
  for (int i = 1; i <= g; ++i) {
    if (g % i == 0) res++;
  }
  return res;
}