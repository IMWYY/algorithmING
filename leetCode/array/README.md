# Array

## Sorting algorithms

### Insertion Sort

- time complexity: O(n^2)
- stable

```java
public void insertSort(int[] a) {
    for (int i = 1; i < a.length; ++i) {
        if (a[i] < a[i - 1]) {
            int j = i - 1, temp = a[i];
            while (j >= 0 && a[j] > temp) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }
}
```

### Shell's Sort

- time complexity: O(n^(1.3-2))
- unstable

```java
// shrink the step from n/2 to 1, perform an insertion sort at each step
public void shellSort(int[] a) {
    int d = a.length / 2;
    while (d >= 1) {
        shellInsertSort(a, d);
        d = d / 2;
    }
}
private void shellInsertSort(int[] a, int d) {
    for (int i = d; i < a.length; ++i) {
        if (a[i - d] > a[i]) {
            int temp = a[i], j = i - d;
            a[i] = a[i - d];
            while (j >= 0 && a[j] > temp) {
                a[j + d] = a[j];
                j -= d;
            }
            a[j + d] = temp;
        }
    }
}
```

### Selection Sort

- time complexity: O(n^2)
- unstable

```java
/**
 * select the minimum element and swap it with current position.
 * a simple improvement: select min and max element at each selsection
 */
public void selectionSort(int[] a) {
    for (int i = 0; i < a.length; ++i) {
        int key = selectMin(a, i), temp;
        if (key != i) {
            temp = a[key];
            a[key] = a[i];
            a[i] = temp;
        }
    }
}

private int selectMin(int[] a, int start) {
    int res = start;
    for (int i = start + 1; i < a.length; ++i)
        if (a[i] < a[res]) res = i;
    return res;
}
```

### Heap Sort (unstable)

- time complexity: O(n * logn)
- unstable

```java
public void heapSort(int[] a) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int i : a) minHeap.add(i);

    for (int i = 0; i < a.length; ++i)
        a[i] = minHeap.poll();
}
```

### Bubble Sort

- time complexity: O(n^2)
- stable

```java
public void bubbleSort(int[] a) {
    int temp;
    for (int i = a.length - 1; i >= 0; --i) {
        for (int j = 1; j <= i; ++j) {
            if (a[j - 1] > a[j]) {
                temp = a[j - 1];
                a[j - 1] = a[j];
                a[j] = temp;
            }
        }
    }
}
```

### Quick Sort

- time complexity: O(n * logn)
- unstable

```java
public void quickSort(int[] a, int left, int right) {
    if (left > right) return;
    int flag = a[left], i = left, j = right, temp;
    while (i < j) {
        while (j > i && a[j] >= flag) j--;
        while (j > i && a[i] <= flag) i++;
        if (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
    a[left] = a[i];
    a[i] = flag;

    quickSort(a, left, i - 1);
    quickSort(a, i + 1, right);
}
```

### Merge Sort

- time complexity: O(n * logn)
- stable

```java
public void mergeSort(int[] a, int left, int right, int[] temp) {
    if (left < right) {
        int mid = (left + right) / 2;
        mergeSort(a, left, mid, temp);
        mergeSort(a, mid + 1, right, temp);
        merge(a, left, mid, right, temp);
    }
}

private void merge(int[] a, int left, int mid, int right, int[] temp) {
    int index = 0, i = left, j = mid + 1;
    while (i <= mid && j <= right) {
        if (a[i] <= a[j]) {
            temp[index++] = a[i++];
        } else {
            temp[index++] = a[j++];
        }
    }

    while (i <= mid) temp[index++] = a[i++];
    while (j <= right) temp[index++] = a[j++];

    for (int k = 0; k < index; ++k) {
        a[left + k] = temp[k];
    }
}
```

```java
/**
 * iterative version
 */
public void mergeSortNonRecursive(int[] a) {
    int[] temp = new int[a.length];
    int len = 1, s, i;
    while (len < a.length) {
        s = len;
        len = 2 * s;
        i = 0;
        // merge sequences with equal length
        while (i + len < a.length) {
            merge(a, i, i + s - 1, i + len - 1, temp);
            i = i + len;
        }

        if (i + s < a.length) {
            merge(a, i, i + s - 1, a.length - 1, temp);
        }
    }
}
```