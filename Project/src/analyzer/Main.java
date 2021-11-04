package analyzer;

import java.io.*;
import java.nio.file.Files;

class TypeAnalyzer {
    /**
     * A strategy for concrete algorithms
     * that will be used to analyze the type.
     * I used the strategy pattern' so I could add algorithms more easily
     * and do encapsulate the algorithms in separated classes
     */
    private final TypeAnalyzingAlgorithm algorithm;

    public TypeAnalyzer(TypeAnalyzingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public boolean analyze(byte[] bytesRead, String pattern, int size) {
        return this.algorithm.getResult(bytesRead, pattern, size);
    }
}

interface TypeAnalyzingAlgorithm {
    /**
     * A context to analyzer algorithm
     * Returns search result in boolean
     * True if found, False if not
     */
    boolean getResult(byte[] bytesRead, String pattern, int size);
}

class NaiveAlgorithm implements TypeAnalyzingAlgorithm {
    /**
     * An implementation of the naive algorithm
     */
    public boolean getResult(byte[] bytesRead, String pattern, int size) {

        int patternLength = pattern.length();

        boolean found = false;

        for (int i = 0; i <= size - patternLength; i++) {

            int j;

            for (j = 0; j < patternLength; j++) {
                if (bytesRead[i + j] != pattern.charAt(j)) {
                    break;
                }
            }

            if (j == patternLength) {
                found = true;
                break;
            }
        }

        return found;
    }
}


class KmpAlgorithm implements TypeAnalyzingAlgorithm {
    /**
     * The lsp array calculation.
     * We will use that array in the KMP algorithm
     */
    private void computeLPSArray(String pattern, int size, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < size) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {    // if pattern[i] != pattern[len]
                if (len != 0) {
                    len = lps[len - 1];
                } else { // if len = 0
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
    /**
     * An implementation of the kmp algorithm
     */
    public boolean getResult(byte[] bytesRead, String pattern, int size) {

        int patternLength = pattern.length();
        int count = 0;
        int i = 0;

        int[] lps = new int[patternLength];

        boolean found = false;

        computeLPSArray(pattern, patternLength, lps);

        while (i < size && !found) {
            if (pattern.charAt(count) == bytesRead[i]) {
                count++;
                i++;
            }
            if (count == patternLength) {
                found = true;
            } else if (i < size && pattern.charAt(count) != bytesRead[i]) {
                if (count != 0) {
                    count = lps[count - 1];
                } else {
                    i++;
                }
            }
        }
        return found;
    }
}

public class Main {
    /**
     * The main function. Takes 4 arguments
     * ( 1.Algorithm - the preferred algorithm ro find the pattern in the binary file,
     *   2.FilePath - the path to the binary file.
     *   3.Pattern - the pattern to search for,
     *   4.Result - the string you want to be printed if the pattern is found in the file)
     *
     *
     * In the case the pattern will not be found in the file,
     * the program will print "Unknown file type"
     */
    public static void main(String[] args) {

        final int minArgs = 4;

        if (args.length != minArgs) {
            System.out.println("invalid amount of arguments.\n" +
                    "Please add an operator (\"+\", \"-\", \"*\")," +
                    " and two integers as the command-line arguments");
            System.exit(1);
        }

        String algorithm = args[0];
        String filePath = args[1];
        String pattern = args[2];
        String result = args[3];

        TypeAnalyzer typeAnalyzer = null;

        boolean found = false;

        switch (algorithm) {
            case "--naive":
                typeAnalyzer = new TypeAnalyzer(new NaiveAlgorithm());
                break;
            case "--KMP":
                typeAnalyzer = new TypeAnalyzer(new KmpAlgorithm());
                break;
            default:
                break;
        }

        if (typeAnalyzer == null) {
            System.out.println("Unknown strategy type passed. Please, write to the author of the problem");
        } else {

            File file = new File(filePath);
            try {
                byte[] bytesRead = Files.readAllBytes(file.toPath());
                found = typeAnalyzer.analyze(bytesRead, pattern, bytesRead.length);

            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }

        System.out.println(found ? result : "Unknown file type");
        System.out.printf("It took %.3f seconds", (double)System.nanoTime() / 1_000_000_000);

    }
}




