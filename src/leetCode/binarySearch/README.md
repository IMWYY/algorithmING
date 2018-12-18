## 二分查找

参见我写的[文章](https://blog.csdn.net/C_J33/article/details/80204972)

### 普通二分查找key

```java
private static int binarySearch0(int[] a, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
        int mid = (low + high) >>> 1;
        int midVal = a[mid];

        if (midVal < key)
            low = mid + 1;
        else if (midVal > key)
            high = mid;
        else
            return mid; // key found
} 
```



### 二分查找key第一次出现的位置

```java
private static int binarySearch1(int[] a, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex;

    while (low < high) {
        int mid = low + (high-low)>>1;
        int midVal = a[mid];

         // 如果中间值比target小，left移到mid+1
        if (midVal < key)
            low = mid + 1;
        else 
            // 如果中间值不比target小即可能等于，right移到mid
            high = mid;
} 
```



### 二分查找key最后一次出现的位置

```java
// 可以看成是从后往前找第一次出现的位置
private static int binarySearch1(int[] a, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex;

    while (low < high) {
        int mid = high - ((high-low)>> 1);//注意这里！！ 不然会死循环
        int midVal = a[mid];

        if (midVal > key)
            high = mid - 1;
        else 
            low = mid;
} 
```



### Tips

- 二分查找可能不止是变动下标，还有可能是变动值来二分查找。
- 二分查找需要找到可以继续按照原条件二分的子结构