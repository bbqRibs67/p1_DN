import java.util.Scanner;

public class DN03_63250270 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int k = sc.nextInt();   
        long result = 0;

        int maxSide = (int)Math.pow(2, k);
        if (k == 30)
            maxSide /= 2;
        int relevantWidth = w, relevantHeight = h;
        int hCut, wCut;

        short intersectCheck;
        for (int size = 2; size <= maxSide; size *= 2) {
            intersectCheck = 0;
            hCut = relevantHeight % size;
            wCut = relevantWidth % size;
            if (hCut != 0) {
                result += relevantWidth / hCut;
                intersectCheck = 1;
            }
            if (wCut != 0) {
                result += relevantHeight / wCut - intersectCheck % 2;
            }
            relevantHeight -= hCut;
            relevantWidth -= wCut;
        }
        result += (((long)(relevantHeight) * (relevantWidth)) / ((long)(maxSide)) / maxSide);
        
        System.out.println(result);
        sc.close();
    }
}
