package classic;

import java.util.PriorityQueue;

/**
 * 排序算法实现
 * create by stephen on 2018/4/12
 */
public class Sort {

    /**
     * 直接插入排序 稳定 O(n^2)
     */
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

    /**
     * 希尔排序, 又叫缩小增量排序
     * d从n/2一直缩小到1 每一步都进行简单插入排序 不稳定
     */
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

    /**
     * 简单选择排序 每次选择后面数字的最小值和当前位置交换 O(n^2)
     * 简单选择排序的改进:二元选择排序 每次选出最大和最小的元素
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

    /**
     * 堆排序 利用Java的优先级队列 O(nlogn) 不稳定
     */
    public void heapSort(int[] a) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : a)
            minHeap.add(i);

        for (int i = 0; i < a.length; ++i)
            a[i] = minHeap.poll();
    }

    /**
     * 冒泡排序 稳定 O(n^2)
     */
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

    /**
     * 快速排序 O(nlogn) 不稳定
     */
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

    /**
     * 归并排序 分治的思想 O(nlogn) 稳定
     */
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

    /**
     * 非递归的归并排序
     */
    public void mergeSortNonRecursive(int[] a) {
        int[] temp = new int[a.length];
        int len = 1, s, i;
        while (len < a.length) {
            s = len;
            len = 2 * s;
            i = 0;
            // 把等长度的子序列合并
            while (i + len < a.length) {
                merge(a, i, i + s - 1, i + len - 1, temp);
                i = i + len;
            }

            if (i + s < a.length) {
                merge(a, i, i + s - 1, a.length - 1, temp);
            }
        }
    }

}