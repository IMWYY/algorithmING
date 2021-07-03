# Math

## Reservoir Sample

```c++
// A function to randomly select k items from stream[0..n-1].
std::vector<int> selectKItems(std::vector<int>& stream, int k) {
  int n = stream.size();
  int i;  // index for elements in stream[]

  // reservoir[] is the output array. Initialize
  // it with first k elements from stream[]
  std::vector<int> reservoir;
  for (i = 0; i < k; i++) reservoir[i] = stream[i];

  // Use a different seed value so that we don't get
  // same result each time we run this program
  srand(time(NULL));

  // Iterate from the (k+1)th element to nth element
  for (; i < n; i++) {
    // Pick a random index from 0 to i.
    int j = rand() % (i + 1);

    // If the randomly picked index is smaller than k,
    // then replace the element present at the index
    // with new element from stream
    if (j < k) reservoir[j] = stream[i];
  }
  return reservoir;
}
```

## Greatest Common Divisor (GCD)

```c++
// gcd(a, b) = gcd(b, a mod b)
int gcd_v1(int a, int b) {
  if (b == 0) return a;
  return gcd_v1(b, a % b);
}

int gcd_v2(int a, int b) {
  if (a == b)
    return a;
  else if (a > b)
    return gcd_v2(a - b, b);
  else
    return gcd_v2(a, b - a);
}
```