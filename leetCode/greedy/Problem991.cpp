
// greedy,
// Y > X: chose Y/2 when Y is even and Y+1 when y is odd
// Y < X: return X-Y
int brokenCalc(int X, int Y) {
  if (X >= Y) return X - Y;
  return (Y & 1) == 0 ? 1 + brokenCalc(X, Y / 2) : 1 + brokenCalc(X, Y + 1);
}