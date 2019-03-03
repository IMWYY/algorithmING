package leetCode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * create by stephen on 2018/10/23
 */
public class Problem412 {
    /**
     * 普通思路
     */
    public List<String> fizzBuzz1(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            if (i % 5 == 0 && i % 3 == 0) {
                res.add("FizzBuzz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }

    /**
     * 如果条件增加 可以应对多条件
     */
    public List<String> fizzBuzz2(int n) {
        List<String> ans = new ArrayList<String>();
        for (int num = 1; num <= n; num++) {
            boolean divisibleBy3 = (num % 3 == 0);
            boolean divisibleBy5 = (num % 5 == 0);
            String numAnsStr = "";
            if (divisibleBy3) {
                // Divides by 3, add Fizz
                numAnsStr += "Fizz";
            }
            if (divisibleBy5) {
                // Divides by 5, add Buzz
                numAnsStr += "Buzz";
            }
            if (numAnsStr.equals("")) {
                // Not divisible by 3 or 5, add the number
                numAnsStr += Integer.toString(num);
            }
            // Append the current answer str to the ans list
            ans.add(numAnsStr);
        }
        return ans;
    }
}
