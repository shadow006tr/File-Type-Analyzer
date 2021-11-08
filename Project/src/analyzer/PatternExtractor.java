package analyzer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PatternExtractor {

    /**
     * A class with a single method, extracting patterns from a text file
     * and arranging them into a list of Pattern objects.
     * The structure of the patterns file should be as follows:
     *
     * int Priority; String "Pattern"; String "Result"
     * int Priority; String "Pattern"; String "Result"
     * ...
     *
     * Priority - the priority of the pattern. Higher priority patterns will be checked first
     * Pattern - the pattern to check.
     * Result - the result to be printed if the pattern is found in the file.
     *
     * The method returns a list of Pattern objects,
     * each of whom holds the Priority, Pattern and Result in separate fields.
     */

    public List<Pattern> run(String patternsDB) {
        List<Pattern> patterns = new ArrayList<>();                                     // Creating an empty list
                                                                                        // of Pattern objects.
        try {
            patterns = Arrays.stream(Files.readString(Path.of(patternsDB))              // Streaming the pattern DB
                            .split("(\\r\\n|[\\n\\r\\u2028\\u2029\\u0085])"))           // Splitting the lines
                    .map((line) -> {
                        String[] data = line.split(";");                                // Splitting every line
                                                                                        // based on the chars ';'

                        Function<String, String> removeQuotes = (string) ->             // A small lambda to remove
                                string.charAt(0) == '\"' &&                             // quotes from the beginning
                                        string.charAt(string.length() - 1) == '\"' ?    // and end of strings
                                        string.substring(1, string.length() - 1) : string;

                        data[1] = removeQuotes.apply(data[1]);                          // Applying the lambda on the
                        data[2] = removeQuotes.apply(data[2]);                          // Patterns and the Result

                        return new Pattern(                                             //Returning the Pattern object
                                Integer.parseInt(data[0]),                              // with the extracted data
                                data[1],
                                data[2]
                        );
                    })
                    .collect(Collectors.toList());
        }catch (Exception e) {                                                          // catching errors
            e.getStackTrace();
        }
            return patterns;
    }
}
