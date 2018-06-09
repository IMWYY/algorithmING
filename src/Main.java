import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();

        int money = 0, moneyCanDiscount = 0;
        double result;

        for (int i = 0; i < n; ++i) {
            int a = in.nextInt(), b = in.nextInt();
            if (b == 0) money += a;
            else moneyCanDiscount += a;
        }

        int discount = 0;
        for (int i = 0; i < m; ++i) {
            int a = in.nextInt(), b = in.nextInt();
            if (money + moneyCanDiscount >= a && b > discount) {
                discount = b;
            }
        }

        result = Math.min(money + moneyCanDiscount * 0.8, money + moneyCanDiscount-discount);

        System.out.println(String.format("%.2f", result));
    }
}