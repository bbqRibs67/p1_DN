import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DN0TestCaseGenerator {
    /*
    to je basically use k je treba spremenit (poleg izračuna resulta)
    number - št. naloge (DN0x)
    input ranges - intervali ([x, y]) inputa, po vrsti kot jih beremo
    continuous input ranges - intervali stvari k jih bere n krat (n je vrednost enega izmed vhodov)
    continuous input size index - indeksi vrednosti, ki povejo kolikokrat se en instance cont. inputa bere
    input - uhodi

    folder name je univerzalen: ./DN0x_Testi (treba nardit u naprej)
     */
    static int number = 4;
    static int tests = 67;
    static int[][] inputRanges = {
            {1, 10000},
            {2, 2000}
    };
    static int[][] continuousInputRanges = {
            {1, 1000}
    };
    static int[] continuousInputSizeIndexes = {0};

    static int[] inputs = new int[inputRanges.length];
    static int[][] continuousInputs = new int[continuousInputRanges.length][];

    public static void writeOut(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename + ".out");

        //parametri, po vrsti kako se bere ponavad
        long solution = solve(inputs[0], inputs[1], continuousInputs[0]);

        fileWriter.write("" + solution);
        fileWriter.write("\n");
        fileWriter.close();
    }

    //metoda k res nalogo, parametri (1. input, 2. input... , recimo se kaksn 1. [] input, 2. [] input, ...)
    public static long solve(int n, int k, int[] narr) {
        int[] v = new int[2001];
        for (int i = 0; i < n; i++) {
            v[narr[i]]++;
        }

        int j;
        long r = 0;
        for (int i = 0; i < v.length; i++) {
            j = k - i;
            if (j < v.length && j >= 0) {
                r += (long) v[i] * v[j];
            }
        }

        return r;
    }
    //------------------------------------

    public static void main(String[] args) throws IOException {
        String filename;
        new File("./DN0" + number + "_Testi").mkdirs();
        for (int i = 0; i < tests; i++) {
            filename =  "./DN0" + number + "_Testi/test" + ((i < 10) ? "0" : "") + i;
            System.out.println(filename);
            generateInputs();
            writeIn(filename);
            writeOut(filename);
        }
    }

    public static void generateInputs() {
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = randomInRange(inputRanges[i][0],  inputRanges[i][1]);
        }

        for (int i = 0; i < continuousInputSizeIndexes.length; i++) {
            continuousInputs[i] = new int[inputs[continuousInputSizeIndexes[i]]];
            for (int j = 0; j < continuousInputs[i].length; j++) {
                continuousInputs[i][j] = randomInRange(continuousInputRanges[i][0], continuousInputRanges[i][1]);
            }
        }
    }

    public static int randomInRange(int low, int high) {
        return (int)(Math.random() * (high - low + 1)) + low;
    }

    public static void writeIn(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename + ".in");
        for (int input : inputs) {
            fileWriter.write("" + input);
            fileWriter.write("\n");
        }
        for (int[] continuousInput : continuousInputs) {
            for (int i : continuousInput) {
                fileWriter.write("" + i + " ");
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}