//
// Created by 王友运 on 2019-03-06.
//

/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * Note: n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 *
 * Example 1:
 * Input: 3
 * Output:3
 *
 * Example 2:
 * Input:11
 * Output:0
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
**/

int findNthDigit(int n) {
    if (n < 10) return n;
    // 找到这个n属于几位数
    int len = 1;
    long tmp = 9;
    while (tmp < n) {
        len++;
        tmp += std::pow(10, len - 1) * 9 * len;
    }
    if (tmp == n) return 9;

    // 找到这个n所在的真实数字
    int offset = n - tmp + std::pow(10, len - 1) * 9 * len;
    int delta = offset / len;
    if (delta * len < offset) {
        delta++;
    }
    int real_num = std::pow(10, len - 1) + delta - 1;

    // 计算偏差 找到结果
    int index = offset % len;
    if (index == 0) {
        return real_num % 10;
    } else {
        while (index < len) {
            real_num /= 10;
            index++;
        }
        return real_num % 10;
    }
}
