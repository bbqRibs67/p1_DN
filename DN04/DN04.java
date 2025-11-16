import java.util.Scanner;

public class DN04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] v = new int[k];
        for (int i = 0; i < n; i++) {
            v[sc.nextInt()]++;
        }

        long r = 0;
        for (int i = 0; i < v.length; i++) {
            int j = k - i;
            if (j < v.length) {
                r += (long) v[i] * v[j];
            }
        }

        System.out.println(r);
        sc.close();
    }
}