package analyzer;

import java.io.File;
import java.nio.file.Files;

public class KmpWorker implements Runnable {

    /**
     * the class of the KmpWorkers. We use them to run on all the files simultaneously,
     * with the KMP String searching algorithm, which is why it is the default run method.
     */
    private final File file;                                                        // private fields
    private final String pattern;                                                   // of the base arguments
    private final String result;

    public KmpWorker(File file, String pattern, String result) {

        this.file = file;                                                           // A object constructor,
        this.pattern = pattern;                                                     // with private fields init.
        this.result = result;
    }

    /**
     * An implementation of the kmp algorithm
     */
    @Override
    public void run() {

        byte[] fileBytes;                                                           // Creating a byte array.
        try {
            fileBytes = Files.readAllBytes(file.toPath());                          // Trying to read the file's bytes
        } catch (Exception e) {                                                     // to a byte array.
            e.getStackTrace();
            return;                                                                 // Throwing an exception if couldn't.
        }
        int fileSize = fileBytes.length;                                            // Getting the byte array's length
        int patternLength = pattern.length();                                       // and the pattern's length.
        int count = 0;                                                              // A counter to check if we found
        // the pattern.
        int i = 0;                                                                  // An index variable.

        int[] lps = new int[patternLength];                                         // Getting an LPS int array
        // from the pattern

        boolean found = false;                                                      // A boolean to check if we
        // found the pattern
        computeLPSArray(pattern, patternLength, lps);

        while (i < fileSize && !found) {                                            // Running on the array
            // in search of the pattern.
            if (pattern.charAt(count) == fileBytes[i]) {                            // If we find a part of the pattern,
                count++;                                                            // we increment the counter
                i++;                                                                // and the index.
            }
            if (count == patternLength) {                                           // if we found the pattern
                found = true;                                                       // we can stop searching.

            } else if (i < fileSize && pattern.charAt(count) != fileBytes[i]) {     // if the next char is different
                // from the one we are on in
                // the pattern, abd we haven't
                // reached the end of the file.
                if (count != 0) {                                                   // then if the counter is not zero,
                    count = lps[count - 1];                                         // we change it based of the lps
                } else {                                                            // array.
                    i++;                                                            // if the counter is zero
                }                                                                   // we just adjust the index
            }                                                                       // in the byte array (file)
        }
        System.out.printf("%s: %s\n", file.getName(), found ? result : "Unknown file type");
        // at the end we print the file's
        // name and result if we found
        // the pattern, or
        // "Unknown file type" if we didn't
    }

    /**
     * The lsp array calculation.
     * We will use that array in the KMP algorithm
     */
    private void computeLPSArray(String pattern, int size, int[] lps) {
        int len = 0;                                                                // The length of the
        // longest previous suffix
        int i = 1;                                                                  // Index in the array
        lps[0] = 0;                                                                 // The first element must be 0.

        while (i < size) {                                                          // running on the array
            if (pattern.charAt(i) == pattern.charAt(len)) {                         // if the prefix letter is equal
                // to the suffix letter,
                len++;                                                              // we increment the length,
                lps[i] = len;                                                       // writing it to the current
                i++;                                                                // position in the array,
                // and then moving on the array.

            } else {                                                                // If pattern[i] != pattern[len],
                if (len != 0) {                                                     // and the length isn't zero,
                    len = lps[len - 1];                                             // we are writing the previous
                    // length in the array.

                } else {                                                            // if the length is zero
                    lps[i] = len;                                                   // we put zero in the current
                    // index in the array
                    i++;                                                            // and move on it.
                }
            }
        }
    }
}
