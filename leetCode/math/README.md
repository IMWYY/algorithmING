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

## Fisher-Yates Shuffle
```c++
std::vector<int> shuffle(std::vector<int> arr) {
  std::vector<int> res(arr);
  for (int i = 0; i < res.size(); ++i) {
    int idx = rand() % (i + 1);
    std::swap(res[idx], res[i]);
  }
  return res;
}
```
Proof: Suppose this algorithm works, i.e. for each position j (starting from 0), the probability of any number in the range[0,j] to be at position j is 1/(1+j).

Let's look at int i = random.nextInt(j + 1):
1. If i == j, nums[j] does not need to change its position, which has probability 1/(1+j).
2. if i !=j, nums[j] needs to be swapped with nums[i]. The probability of any number x in the range [0,j-1] to be at position j = nums[j] changes its position * x is at position i
= (1-1/(1+j)) * (1/j) = 1/(1+j)

Each number has equal probability to be at any position.

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