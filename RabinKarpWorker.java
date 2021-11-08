package analyzer;

import java.util.function.Function;

public class RabinKarpWorker {

    /**
     * An implementation of the RabinKarp algorithm,
     * to be used by the threads and check if the patterns
     * exist in the files.
     *
     * Has a single method for a single Substring search.
     */

    private static final int PRIME = 101;                                           // A prime number to
                                                                                    // calculate hash values

    private static final int ALPHABET = 256;                                        // The length of the
                                                                                    // possible alphabet (radix)

    public static boolean run(String file, String pattern) {

        int fileLength = file.length();                                             // Saving file length
        int patternLength = pattern.length();                                       // and pattern length

        boolean found = false;                                                      // A boolean to indicate that
                                                                                    // we found the pattern

        if (fileLength >= patternLength) {                                          // We star working if the file
                                                                                    // is longer then the pattern

            Function<String, Integer> calculateHash = subString -> {                // A small lambda to calculate
                int result = 0;                                                     // the initial hash of a substring
                for (int i = 0; i < subString.length(); i++) {
                    result = (ALPHABET * result + subString.charAt(i)) % PRIME;
                }
                return result;
            };

            int patternHash = calculateHash.apply(pattern);                         // Calculating the hash value
                                                                                    // of the pattern
            int fileHash = calculateHash.apply(file.substring(0, patternLength));   // and the first substring of the
                                                                                    // file at the pattern's length

            int h = 1;                                                              // Hash key, starts from one,

            int i;                                                                  // Indexes to be used later.
            int j;

            for (i = 0; i < patternLength - 1; i++) {                               // Calculating the hash
                                                                                    // for keys[0 ... patternLength - 1]
                h = (h * ALPHABET) % PRIME;
            }


            for (i = 0; i <= fileLength - patternLength; i++) {                     // Sliding the pattern over the file
                if (patternHash == fileHash) {                                      // If the app finds a matching hash
                    for (j = 0; j < patternLength; j++) {                           // it checks the substrings naively
                        if (file.charAt(i + j) != pattern.charAt(j))
                            break;
                    }
                    if (j == patternLength) {                                       // if they are the same,
                        found = true;                                               // it found a pattern
                    }
                }
                if (i != fileLength - patternLength && !found) {                    // if the pattern wasn't found
                                                                                    // the app calculates the next
                                                                                    // hash of the file, by using
                                                                                    // the rolling hash function.
                    fileHash = (ALPHABET * (fileHash - file.charAt(i) * h) + file.charAt(i + patternLength)) % PRIME;

                    if (fileHash < 0) {                                             // and correcting the result
                                                                                    // if it becomes negative.
                        fileHash = fileHash + PRIME;
                    }
                }
            }
        }                                                                           // If the app finds a pattern
        return found;                                                               // it returns true, false otherwise.
    }
}
