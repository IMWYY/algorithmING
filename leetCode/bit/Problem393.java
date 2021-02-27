package leetCode.bit;

/**
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 * For 1-byte character, the first bit is a 0, followed by its unicode code.
 * For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed
 * by n-1 bytes with most significant 2 bits being 10.
 * This is how the UTF-8 encoding would work:
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 */
public class Problem393 {

    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) return false;
        int numBytes;
        for (int i = 0; i < data.length; ) {
            if (data[i] > 255) return false;
            if ((data[i] & 128) == 0) {
                numBytes = 0;
            } else if ((data[i] & 224) == 192) {
                numBytes = 1;
            } else if ((data[i] & 240) == 224) {
                numBytes = 2;
            } else if ((data[i] & 248) == 240) {
                numBytes = 3;
            } else {
                return false;
            }
            for (int j = i + 1; j <= i + numBytes; ++j) {
                if (j >= data.length) return false;
                if ((data[j] & 192) != 128) return false;
            }
            i += numBytes + 1;
        }
        return true;
    }
}