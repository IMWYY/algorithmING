import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] board = new int[n];
        int[] target = new int[n];
        for (int i = 0; i < n; ++i) {
            board[i] = in.nextInt();
        }
        for (int i = 0; i < n; ++i) {
            target[i] = in.nextInt();
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            // 从后面找
            if (target[i] > 0) {
                for (int j = i; j < n; ++j) {
                    if (board[j] > 0) {
                        if (board[j] >= target[i]) {
                            res += (j - i) * target[i];
                            board[j] -= target[i];
                            target[i] = 0;
                        } else {
                            res += (j - i) * board[j];
                            target[i] -= board[j];
                            board[j] = 0;
                        }
                    }
                    if (target[i] == 0) break;
                }
            }

            // 从前面找
            if (target[i] > 0) {
                for (int j = 0; j < i; ++j) {
                    if (board[j] > 0) {
                        if (board[j] >= target[i]) {
                            res += (j + i) * target[i];
                            board[j] -= target[i];
                            target[i] = 0;
                        } else {
                            res += (j + i) * board[j];
                            target[i] -= board[j];
                            board[j] = 0;
                        }
                    }
                    if (target[i] == 0) break;
                }
            }
        }

        System.out.println(res);
    }
}