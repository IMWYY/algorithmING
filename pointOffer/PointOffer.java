package pointOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointOffer {

    public static void main(String[] args) {
        PointOffer solution = new PointOffer();
        System.out.println(solution.NumberOf1Between1AndN_Solution(2579));
    }

    /**
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
     * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     */
    public boolean isNumeric(char[] str) {
        if (str.length == 0) return false;
        int start = 0;
        if (str[0] == '+' || str[0] == '-') {
            start = 1;
            if (str.length == 1) {
                return false;
            }
        }
        boolean hasDot = false, hasE = false;
        for (int i = start; i < str.length; ++i) {
            if ((str[i] == '.' && hasDot)
                    || ((str[i] == 'e' || str[i] == 'E') && hasE)
                    || (i > start)) {
                return false;
            } else if (str[i] == '.') {
                hasDot = true;
            } else if (str[i] == 'E' || str[i] == 'e') {
                hasE = true;
            }
        }

        return false;
    }


    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。
     * 并将P对1000000007取模的结果输出。 即输出P%1000000007
     */
    public int InversePairs(int[] array) {
        if (array.length == 0) return 0;
        long result = 0;
        return (int) result % 1000000007;
    }


    /**
     * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
     * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
     * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数。
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int i = 1, high = n, low = 0, cur = 0, temp = 0;
        int count = 0;
        while (high != 0) {
            high = n / (int) Math.pow(10, i);
            temp = n % (int) Math.pow(10, i);
            low = temp % (int) Math.pow(10, i - 1);
            cur = temp / (int) Math.pow(10, i - 1);
            if (cur > 1) {
                count += (high + 1) * (int) Math.pow(10, i - 1);
            } else if (cur == 1) {
                count += high * (int) Math.pow(10, i - 1) + low + 1;
            } else {
                count += high * (int) Math.pow(10, i - 1);
            }
            i++;
        }
        return count;
    }


    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        if (str.length() == 0) {
            return result;
        }
        boolean[] used = new boolean[str.length()];
        Arrays.fill(used, false);
        rPermutation(result, str, used, new StringBuilder());
        return result;
    }

    public void rPermutation(ArrayList<String> result, String str, boolean[] used, StringBuilder stringBuilder) {
        if (stringBuilder.length() == str.length()) {
            result.add(stringBuilder.toString());
            return;
        }
        for (int i = 0; i < str.length(); ++i) {
            if (used[i] || (i > 0 && used[i - 1] && str.charAt(i) == str.charAt(i - 1))) {
                continue;
            }
            stringBuilder.append(str.charAt(i));
            used[i] = true;
            rPermutation(result, str, used, stringBuilder);
            used[i] = false;
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }


    /**
     * 输入一个链表，反转链表后，输出链表的所有元素。
     */
    public ListNode ReverseList(ListNode head) {
        ListNode temp, pre = null;
        while (head != null) {
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     *
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     */
    public int JumpFloor(int target) {
        if (target <= 1) {
            return target;
        }
        if (target == 2) {
            return 2;
        }
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }


    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     */
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int left = 0, right = array.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid > 0 && array[mid - 1] > array[mid]) {
                return array[mid];
            }

            if (array[mid] >= array[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return array[left];
    }


    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0 || pre.length != in.length) {
            return null;
        }
        return construct(pre, 0, pre.length - 1, in, 0, in.length - 1);

    }

    public TreeNode construct(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart == preEnd && inStart == inEnd) {
            return new TreeNode(pre[preStart]);
        } else if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);
        int index = -1, lenLeft = 0, lenRight = 0;
        for (int i = inStart; i <= inEnd; ++i) {
            if (in[i] == pre[preStart]) {
                index = i;
                lenLeft = i - inStart;
                lenRight = inEnd - i;
                break;
            }
        }
        if (index == -1) {
            return root;
        }

        root.left = construct(pre, preStart + 1, preStart + lenLeft, in, inStart, index - 1);
        root.right = construct(pre, preEnd - lenRight + 1, preEnd, in, index + 1, inEnd);
        return root;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 请实现一个函数，将一个字符串中的空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */
    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll("\\s", "%20");
    }

    /**
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
     * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。
     * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     */
    public boolean match(char[] str, char[] pattern) {
        if (pattern.length == 0) {
            return str.length == 0;
        }

        if (pattern.length == 2 && pattern[1] == '*') {
            return true;
        }

        return matchCore(str, 0, pattern, 0);
    }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }

        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }

        if (patternIndex < pattern.length - 1 && pattern[patternIndex + 1] == '*') {
            if ((strIndex < str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex < str.length)) {
                return matchCore(str, strIndex, pattern, patternIndex + 2)
                        || matchCore(str, strIndex + 1, pattern, patternIndex)
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2);
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }

        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }


    /**
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
     * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
     */
    public int[] multiply(int[] A) {
        int[] result = new int[A.length];
        if (A.length == 0) {
            return result;
        }
        Arrays.fill(result, 1);
        int temp = 1;
        for (int i = 1; i < A.length; ++i) {
            temp *= A[i - 1];
            result[i] = temp;
        }

        temp = 1;
        for (int i = A.length - 2; i >= 0; --i) {
            temp *= A[i + 1];
            result[i] *= temp;
        }

        return result;
    }


    /**
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
     * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        for (int a : numbers) {
            if (a < 0) {
                if (numbers[-a] < 0) {
                    duplication[0] = -a;
                    return true;
                } else {
                    numbers[-a] = -numbers[-a];
                }
            } else {
                numbers[a] = -numbers[a];
            }
        }

        return false;
    }

    /**
     * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
     * 数值为0或者字符串不是一个合法的数值则返回0
     */
    public int StrToInt(String str) {
        boolean neg = false;
        if (str.startsWith("+")) {
            str = str.substring(1);
        } else if (str.startsWith("-")) {
            str = str.substring(1);
            neg = true;
        }

        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            return 0;
        }

        int result = 0;
        for (int i = str.length() - 1; i >= 0; --i) {
            if (neg) {
                result -= (str.charAt(i) - 48) * Math.pow(10, str.length() - 1 - i);
            } else {
                result += (str.charAt(i) - 48) * Math.pow(10, str.length() - 1 - i);
            }
        }

        return result;
    }


    /**
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     */
    public int Add(int num1, int num2) {
        if (num1 == 0) {
            return num2;
        }
        if (num2 == 0) {
            return num1;
        }

        if (num1 == num2) {
            return num1 << 1;
        }
        int temp;
        while (num1 != 0) {
            temp = num1 ^ num2;
            num1 = (num1 & num2) << 1;
            num2 = temp;
        }
        return 0;
    }

    /**
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
     */
    public int Sum_Solution(int n) {
        int ans = n;
        boolean temp = (ans > 0) && (ans += Sum_Solution(n - 1)) > 0;
        return ans;
    }


    /**
     * 有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
     * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,
     * 从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,
     * 并且拿到牛客名贵的“名侦探柯南”典藏版。哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }

        int result = 0;
        for (int i = 2; i <= n; ++i) {
            result = (result + m - 1) % i;
        }

        return result;
    }


    /**
     * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
     * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,
     * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
     * LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
     * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4)
     * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。为了方便起见,你可以认为大小王是0
     */
    public boolean isContinuous(int[] numbers) {
        if (numbers.length != 5) {
            return false;
        }
        Arrays.sort(numbers);

        int offset = 0, num0 = 0;
        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] != 0) {
                if (i > 0 && numbers[i - 1] > 0) {
                    int temp = numbers[i] - numbers[i - 1];
                    if (temp == 0) {
                        return false;
                    } else {
                        offset += (temp - 1);
                    }
                }
            } else {
                num0++;
            }
        }

        return num0 >= offset;
    }

    /**
     * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
     * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
     * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     */
    public String ReverseSentence(String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] temp = str.split(" ");
        for (int i = temp.length - 1; i >= 0; --i) {
            if (!temp[i].equals(" ")) {
                stringBuilder.append(temp[i]);
                stringBuilder.append(" ");
            }
        }
        if (stringBuilder.length() > 1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }


    /**
     * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
     * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
     * 请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
     * 要求输出循环左移3位后的结果，即“XYZdefabc”。
     */
    public String LeftRotateString(String str, int n) {
        if (n > str.length()) {
            return "";
        }
        String s = str + str;
        return s.substring(n, n + str.length());
    }

    /**
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，
     * 使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();

        int start = 0, end = array.length - 1;

        while (start < end) {
            if (array[start] + array[end] == sum) {
                if (result.size() == 0) {
                    result.add(array[start]);
                    result.add(array[end]);
                    break;
                }
            } else if (array[start] + array[end] > sum) {
                while (array[start] + array[end] > sum) {
                    end--;
                }
            } else {
                while (array[start] + array[end] < sum) {
                    start++;
                }
            }
        }

        return result;
    }


    /**
     * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
     * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
     * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
     * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列?
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (sum < 3) {
            return result;
        }

        int end = (int) Math.sqrt(sum), k = 0;

        for (int l = end; l >= 2; --l) {
            if (2 * sum % l == 0) {
                k = 2 * sum / l;
                if ((k % 2 == 0 && l % 2 == 1) || (k % 2 == 1 && l % 2 == 2)) {
                    int an = (k + l - 1) / 2, a1 = (k - l + 1) / 2;
                    ArrayList<Integer> integers = new ArrayList<>();
                    for (int i = a1; i <= an; ++i) {
                        integers.add(i);
                    }
                    result.add(integers);
                }
            }
        }

        return result;
    }
}