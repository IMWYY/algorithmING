#include <cmath>

/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * Note: n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 */
int findNthDigit(int n) {
    if (n < 10) return n;
    // step1: find the length of the number where nth digit is from
    int len = 1;
    long tmp = 9;
    while (tmp < n) {
        len++;
        tmp += std::pow(10, len - 1) * 9 * len;
    }
    if (tmp == n) return 9;

    // step2: find the actual number where nth digit is in
    int offset = n - tmp + std::pow(10, len - 1) * 9 * len;
    int delta = offset / len;
    if (delta * len < offset) {
        delta++;
    }
    int real_num = std::pow(10, len - 1) + delta - 1;

    // step3: calculate the offset and return the result
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
