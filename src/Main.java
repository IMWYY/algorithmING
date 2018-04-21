import java.util.Arrays;
import java.util.Scanner;

/**
 * create by stephen on 2018/4/2
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (s.length() <= 1) {
            System.out.println(s);
            return;
        }

        int[] d = new int[s.length()];
        Arrays.fill(d, 1);

        String[] strings = new String[s.length()];
        Arrays.fill(strings, "");
        strings[0] = String.valueOf(s.charAt(0));

        int temp, index;

        for (int i = 1; i < d.length; ++i) {
            temp = index = -1;
            for (int j = 0; j < i; ++j) {
                if (s.charAt(i) > s.charAt(j) && d[j] > temp) {
                    temp = d[j];
                    index = j;
                }
            }

            if (index == -1) {
                strings[i] = String.valueOf(s.charAt(i));
            } else {
                strings[i] = strings[index] + s.charAt(i);
            }
        }

        System.out.println(strings[strings.length - 1]);
    }
}


//    private static int result = 0;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n =sc.nextInt(), m = sc.nextInt();
//        int[][] condition = new int[n][2];
//        for (int i=0; i<n; ++i) {
//            condition[i][0] = sc.nextInt();
//            condition[i][1] = sc.nextInt();
//        }
//
//        int[] ans = new int[n];
//        int sum = 0;
//        for (int i=0; i<n; ++i) {
//            ans[i] = condition[i][0];
//            sum += ans[i];
//        }
//
//        if (sum > m) {
//            System.out.println(0);
//            return;
//        }
//
//        solve(condition, sum, ans, 0, m);
//        System.out.println(result);
//
//    }
//
//    public static void solve(int[][] condition, int sum, int[] ans,  int index, int m) {
//        if (sum > m) {
//            return;
//        }
//        result ++;
//
//        for (int i=index; i<condition.length; ++i) {
//            while (condition[i][1] >= ans[i]) {
////                int[] temp = Arrays.copyOf(ans, ans.length);
//                ans[i] ++;
//                solve(condition, sum + 1, ans, i, m);
//            }
//        }
//    }
//}


//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int[] numbers = new int[3];
//        numbers[0] = sc.nextInt();
//        numbers[1] = sc.nextInt();
//        numbers[2] = sc.nextInt();
//        Arrays.sort(numbers);
//
//        int result = 0;
//
//        while (numbers[2] - numbers[0] > 1) {
//            numbers[0] += 2;
//            result ++;
//        }
//
//        while (numbers[2] - numbers[1] > 1) {
//            numbers[1] += 2;
//            result ++;
//        }
//
//        Arrays.sort(numbers);
//
//        if (numbers[0] == numbers[1] && numbers[1] == numbers[2]) {
//            System.out.println(result);
//        } else if (numbers[0] == numbers[1]) {
//            System.out.println(result + 1);
//        } else {
//            System.out.println(result + 2);
//        }
//    }
//}


//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        if (n <= 3) {
//            System.out.println(n);
//            return;
//        }
//        int[] dp = new int[n + 2];
//        dp[0] = 0;
//        dp[1] = 1;
//        for (int i = 2; i <= n + 1; i += 2) {
//            dp[i] = dp[i / 2] + 1;
//            dp[i - 1] = Math.min(dp[i - 2], dp[i]) + 1;
//        }
//
//        System.out.println(dp[n]);
//    }
//}

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        long n = sc.nextLong();
//        if (n <= 3) {
//            System.out.println(n);
//            return;
//        }
//
//        System.out.println(solve(n));
//
//    }
//
//    public static long solve(long num) {
//        if (num <= 3) {
//            return num;
//        }
//
//        if ((num & 1) == 0) {
//            return solve(num / 2) + 1;
//        } else {
//            return Math.min(solve(num + 1), solve(num - 1)) + 1;
//        }
//    }
//}


//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        switch (n) {
//            case 1:
//                System.out.println(1);
//                return;
//            case 2:
//                System.out.println(2);
//                return;
//            case 3:
//                System.out.println(3);
//                return;
//            case 4:
//                System.out.println(4);
//                return;
//            case 5:
//                System.out.println(5);
//                return;
//        }
//        int[] numbers = new int[n + 1];
//        numbers[0] = 1;
//        numbers[1] = 1;
//        numbers[2] = 2;
//        numbers[3] = 3;
//        numbers[4] = 4;
//        numbers[5] = 5;
//        for (int i = 6; i <= n; ++i) {
//            int min2 = 0, min3 = 0, min5 = 0;
//            int temp = 0;
//            for (int j = 1; j < i; ++j) {
//                temp = numbers[j] * 2;
//                if (temp > numbers[i - 1]) {
//                    min2 = temp;
//                    break;
//                }
//            }
//
//            for (int j = 1; j < i; ++j) {
//                temp = numbers[j] * 3;
//                if (temp > numbers[i - 1]) {
//                    min3 = temp;
//                    break;
//                }
//            }
//
//            for (int j = 1; j < i; ++j) {
//                temp = numbers[j] * 5;
//                if (temp > numbers[i - 1]) {
//                    min5 = temp;
//                    break;
//                }
//            }
//
//            numbers[i] = Math.min(min2, Math.min(min3, min5));
//        }
//
//        System.out.println(numbers[n]);
//    }
//}


//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] numbers = new int[n + 1];
//        Arrays.fill(numbers, 0);
//        for (int i = 0; i < n; ++i) {
//            numbers[sc.nextInt()] = 1;
//        }
//
//        for (int i = 0; i < n + 1; ++i) {
//            if (numbers[i] == 0) {
//                System.out.println(i);
//                break;
//            }
//        }
//    }
//}


//    private static long result = 0;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String s = sc.next();
//        boolean[] removed = new boolean[s.length()];
//        Arrays.fill(removed, false);
//        backTrack(s, removed, 0, 0);
//        System.out.println(result);
//
//    }
//
//    public static void backTrack(String s, boolean[] removed, int len, int start) {
//        if (isPan(s, removed)) {
//            result++;
//        }
//
//        for (int i = start; i < s.length(); ++i) {
//            if (!removed[i]) {
//                removed[i] = true;
//                backTrack(s, removed, len - 1, i + 1);
//                removed[i] = false;
//            }
//        }
//    }
//
//    public static boolean isPan(String s, boolean[] removed) {
//        int l = 0, r = s.length() - 1;
//        while (l < r) {
//            while (removed[l] && r > l) l++;
//            while (removed[r] && r > l) r--;
//            if (s.charAt(l) == s.charAt(r)) {
//                l++;
//                r--;
//            } else {
//                return false;
//            }
//        }
//        return true;
//    }
//}

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt(), left = 0, right = 0, count = 0;
//        String s;
//        boolean error;
//        for (int i = 0; i < n; ++i) {
//            s = sc.next();
//            if ((s.length() & 1) == 1) {
//                System.out.println("No");
//                continue;
//            }
//
//            left = count = right = 0;
//            error = false;
//            for (int j = 0; j < s.length(); ++j) {
//                if (s.charAt(j) == '(') {
//                    left++;
//                } else if (s.charAt(j) == ')') {
//                    right++;
//                } else {
//                    error = true;
//                    break;
//                }
//                if (right > left) {
//                    count++;
//                }
//            }
//
//            if (!error && left == right && (count + 1) / 2 <= 1) {
//                System.out.println("Yes");
//            } else {
//                System.out.println("No");
//            }
//        }
//    }
//}

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        long n = sc.nextLong(), num = 0, temp = 0;
////        int n = 1, num = 100, temp = 0;
//        for (int i=0; i<n; ++i) {
//            num = sc.nextLong();
//            if ((num & 1) == 1) {
//                System.out.println("No");
//                continue;
//            }
//            temp = 0;
//            while ((num & 1) == 0) {
//                num /= 2;
//                temp ++;
//            }
//            System.out.println(num + " " + (long)Math.pow(2, temp));
//        }
//    }
//}

//    private static long result = 0;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int k = sc.nextInt(), a = sc.nextInt(), numA = sc.nextInt(), b = sc.nextInt(), numB = sc.nextInt();
//
//        if (numA * a + numB * b < k) {
//            System.out.println(0);
//            return;
//        }
//
//        if (a == b) {
//            if (k >= a && k % a == 0 && k / a <= numA + numB) {
//                result = c(numA + numB, k / a);
//            }
//        } else if (a > b && a % b == 0) {
//
//        } else if (a < b && b % a == 0) {
//
//        } else {
//            int m = k / a, n = 0;
//            while (m >= 0) {
//                if ((k - m * a) % b == 0) {
//                    n = (k - m * a) / b;
//                    break;
//                }
//                m --;
//            }
//
//            if (m < 0) {
//                System.out.println(0);
//                return;
//            }
//
//            if (m <= numA && n <= numB) {
//                result = c(numA, m) * c(numB, n);
//            }
//        }
//
//        System.out.println(result % 1000000007);
//    }
//
//    public static long c(int n, int m) {
//        if (n < m) return 0;
//        long res = 1;
//        for (int i = 1; i <= n; ++i) {
//            res *= i;
//        }
//
//        for (int i = 1; i <= m; ++i) {
//            res /= i;
//        }
//
//        for (int i = 1; i <= n - m; ++i) {
//            res /= i;
//        }
//        return res;
//    }
//}


//    private static long result = 0;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int k = sc.nextInt(), a = sc.nextInt(), numA = sc.nextInt(),b = sc.nextInt(), numB = sc.nextInt();
////        int k = 5, a = 2, numA = 3, b = 3, numB = 3;
//        int[] data = new int[numA + numB];
//        Arrays.fill(data, a);
//        for (int i = numB; i < data.length; ++i) {
//            data[i] = b;
//        }
//
//        backTrack(data, 0, k);
//
//        System.out.println(result % 1000000007);
//
//    }
//
//    public static void backTrack(int[] data, int start, int target) {
//        if (target == 0) {
//            result++;
//            return;
//        } else if (target < 0 || start >= data.length) {
//            return;
//        }
//
//        for (int i = start; i < data.length; ++i) {
//            backTrack(data, i + 1, target - data[i]);
//        }
//    }
//}


//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt(), m = sc.nextInt();
////        int n = 4, m = 1;
//        int count = n / (2 * m);
//        long start = (1 + m + 2 * m) * m / 2;
//        long end = (n + n - m + 1) * m / 2;
//        long positive = (start + end) * count / 2;
//
//        start = (1 + m) * m / 2;
//        end = (n - 2 * m + 1 + n - m) * m / 2;
//        long negative = (start + end) * count / 2;
//
//        System.out.println(positive - negative);
//    }
//}

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String s = sc.next();
//
//        if (s.length() != 39) {
//            System.out.println("Error");
//            return;
//        }
//
//        for (int i = 0; i < s.length(); ++i) {
//            if ((i % 5 == 4 && s.charAt(i) != ':') ||
//                    (i % 5 != 4 && !((s.charAt(i) >= '0' && s.charAt(i) <= '9') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'F')))) {
//                System.out.println("Error");
//                return;
//            }
//        }
//
//        if (s.equals("0000:0000:0000:0000:0000:0000:0000:0000")) {
//            System.out.println("Unspecified");
//        } else if (s.equals("0000:0000:0000:0000:0000:0000:0000:0001")) {
//            System.out.println("Loopback");
//        } else if (s.substring(0, 2).equals("FE")) {
//            if (s.charAt(2) == '8' || s.charAt(2) == '9' || s.charAt(2) == 'A' || s.charAt(2) == 'B') {
//                System.out.println("LinkLocal");
//            } else if (s.charAt(2) >= 'C' && s.charAt(2) <= 'F') {
//                System.out.println("SiteLocal");
//            }
//        } else if (s.substring(0, 2).equals("FF")) {
//            System.out.println("Multicast");
//        } else {
//            System.out.println("GlobalUnicast");
//        }
//    }
//
//}


//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int money = sc.nextInt();
//        List<Integer> list = new ArrayList<>();
//        while (sc.hasNext()) {
//            list.add(sc.nextInt());
//        }
//        int[] cost = new int[list.size() / 2];
//        int[] earn = new int[list.size() / 2];
//        int[] chosen = new int[list.size() / 2];
//
//        for (int i = 0; i < list.size(); ++i) {
//            if (i < list.size() / 2) {
//                cost[i] = list.get(i);
//                chosen[i] = 0;
//            } else {
//                earn[i - list.size() / 2] = list.get(i);
//            }
//        }
//
//        // d[i][j]表示前i个物品放入容量为j的背包的最大价值
//        // d[i][j] = max(d[i-1][j], d[i-1][j-weights[i]] + values[i])
//        int[][] d = new int[cost.length][money + 1];
//
//        for (int i = 0; i < d.length; ++i) {
//            for (int j = 1; j < money + 1; ++j) {
//                if (i == 0) {
//                    if (cost[i] <= j) {
//                        d[i][j] = cost[i];
//                    }
//                } else {
//                    if (j >= cost[i]) {
//                        d[i][j] = Math.max(d[i - 1][j], d[i - 1][j - cost[i]] + earn[i]);
//                    } else {
//                        d[i][j] = d[i - 1][j];
//                    }
//                }
//            }
//        }
//
//        int ii = cost.length - 1, jj = money;
//        while (ii >= 0) {
//            if (ii > 0) {
//                if (jj-cost[ii] >= 0 && d[ii][jj] == d[ii - 1][jj - cost[ii]] + earn[ii]) {
//                    chosen[ii] = 1;
//                    jj -= cost[ii];
//                }
//            } else {
//                if (cost[ii] <= jj) {
//                    chosen[ii] = 1;
//                }
//            }
//            ii--;
//        }
//
//        boolean first = true;
//        for (int i = 0; i < chosen.length; ++i) {
//            if (chosen[i] == 1) {
//                if (first) {
//                    System.out.print(i + 1);
//                    first = false;
//                } else {
//                    System.out.print(" " + (i + 1));
//                }
//            }
//        }
//    }
//}


//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.next();
//        if (s.length() <= 1) {
//            System.out.println(s.length());
//            return;
//        }
//        int[][] d = new int[s.length()][s.length()];
//
//        for (int i = s.length() - 1; i >= 0; --i) {
//            for (int j = i + 1; j < s.length(); ++j) {
//                d[i][i] = 1;
//                if (s.charAt(i) == s.charAt(j)) {
//                    d[i][j] = d[i + 1][j - 1] + 2;
//                } else {
//                    d[i][j] = Math.max(d[i + 1][j], d[i][j - 1]);
//                }
//            }
//        }
//
//        System.out.println(d[0][s.length()-1]);
//    }
//}
