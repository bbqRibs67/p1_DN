import java.io.*;

public class DN0TestCaseGenerator {
    /*
    to je basically use k je treba spremenit (poleg izračuna resulta)
    programPath - js mam to po folderjih (kt vidte na githubu, kjer je root projekta dir DN), drgac pa sam spremente
    number - št. naloge (DN0x)
    input ranges - intervali ([x, y]) inputa, po vrsti kot jih beremo
    continuous input ranges - intervali stvari k jih bere n krat (n je vrednost enega izmed vhodov)
    continuous input size index - indeksi vrednosti, ki povejo kolikokrat se en instance cont. inputa bere
    input - uhodi

    folder name je univerzalen: ./DN0x_Testi (treba nardit u naprej)
     */
    //------------------------------------
    static String programPath = "./DN04/DN04_63250270.java";
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
    //------------------------------------

    public static void main(String[] args) throws IOException {
        String filename;
        new File("./DN0" + number + "_Testi").mkdirs();
        for (int i = 0; i < tests; i++) {
            filename =  "./DN0" + number + "_Testi/test" + ((i < 10) ? "0" : "") + i;
            System.out.println(filename);
            generateInputs();

            ProcessBuilder processBuilder = new ProcessBuilder("java", programPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedWriter processInputWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writeIn(filename, processInputWriter);
            processInputWriter.close();

            BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            writeOut(filename, processOutputReader);
            processOutputReader.close();
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

    public static void writeOut(String filename, BufferedReader processOutputReader) throws IOException {
        FileWriter fileWriter = new FileWriter(filename + ".out");

        String outputLine;
        while ((outputLine = processOutputReader.readLine()) != null) {
            fileWriter.write(outputLine);
            fileWriter.write("\n");
        }

        fileWriter.close();
    }

    public static void writeIn(String filename, BufferedWriter processInputWriter) throws IOException {
        FileWriter fileWriter = new FileWriter(filename + ".in");

        for (int input : inputs) {
            fileWriter.write("" + input);
            fileWriter.write("\n");
            processInputWriter.write("" + input);
            processInputWriter.write("\n");
        }

        for (int[] continuousInput : continuousInputs) {
            for (int i : continuousInput) {
                fileWriter.write("" + i + " ");
                processInputWriter.write("" + i + " ");
            }
            fileWriter.write("\n");
            processInputWriter.write("\n");
        }

        fileWriter.close();
    }
}