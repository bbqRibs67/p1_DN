import java.util.Scanner;

public class DN01_63250270 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }

        int r = (a - 1) * (b - 1);
        for (int sqa = 2; sqa <= a; sqa++)
            r += (a - sqa) * (b - sqa);
        
        System.out.println(r);
        sc.close();
    }
}
