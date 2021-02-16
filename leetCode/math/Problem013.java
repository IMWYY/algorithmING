package leetCode.math;

import java.util.HashMap;
import java.util.Map;

public class Problem013 {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        for (int i=0; i<s.length(); ++i) {
            if (i == 0) {
                res += map.get(s.charAt(i));
            } else {
                char former = s.charAt(i-1);
                if (map.get(former) < map.get(s.charAt(i))) {
                    res += map.get(s.charAt(i)) -2 * map.get(former);
                } else {
                    res += map.get(s.charAt(i));
                }
            }
        }
        return res;
    }
}
