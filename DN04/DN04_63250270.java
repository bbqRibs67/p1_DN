import java.util.Scanner;

public class DN04_63250270 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] v = new int[2001];
        for (int i = 0; i < n; i++) {
            v[sc.nextInt()]++;
        }

        int j;
        long r = 0;
        for (int i = 0; i < v.length; i++) {
            j = k - i;
            if (j < v.length && j >= 0) {
                r += (long) v[i] * v[j];
            }
        }

        System.out.println(r);
        sc.close();
    }
}