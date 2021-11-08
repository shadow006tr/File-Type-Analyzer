package analyzer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {
    /**
     * The main function. Takes 2 arguments
     * ( 1.Folder - Folder with files to check)
     *   2.Pattern - the pattern and result database
     *   (the base is supposed to be structured like this:
     *
     *   int Priority; String "Pattern"; String "Result"
     *   int Priority; String "Pattern"; String "Result"
     *   ...
     *   ( the quotes are optional, see pattersDB_Example.txt for example )
     *
     *   Priority - the priority of the pattern. Higher priority patterns will be checked first
     *   Pattern - the pattern to check
     *   Result - the result to be printed if the pattern is found in the file)
     *
     * the app will run a multithreaded work (each file as a thread)
     * and execute the RabinKarp Substring search algorithm
     */
    public static void main(String[] args) throws InterruptedException, IOException {

        final int argNum = 2;                                                       // The amount of arguments needed

        if (args.length != argNum) {                                                // Tf the amount of arguments
            System.out.printf("invalid amount of arguments.\n" +                    // will not be correct,
                    "Please add %d operators,\n" +                                  // the app will exit with an error.
                    "a path to a folder from which to take the files" +
                    "and a text database of patterns " +
                    "and results to print if the pattern is found", argNum);
            System.exit(1);
        }

        List<File> filesList = Files.walk(Path.of(args[0]))                         // Creating a list of all the files
                .filter(Files::isRegularFile)                                       // the app needs to run on
                .map(Path::toFile)                                                  // from the specified directory
                .collect(Collectors.toList());

        String patternsDB = args[1];                                                // Getting the patterns DataBase

        List<Pattern> patterns = new PatternExtractor().run(patternsDB);            // Extracting the pattern DataBase
                                                                                    // to a list of pattern objects.

        ExecutorService executor = Executors.newFixedThreadPool(filesList.size());  // Creating a Executor,
                                                                                    // based on the amount of files
                                                                                    // the app needs to check.

        Collection<Callable<String>> callables = new ArrayList<>();                 // Creating a Collection of callables
                                                                                    // to get the results from the jobs

        for (File file : filesList) {                                               // Foreach file in the list,
                                                                                    // determine the result
            String result = String.format("%s: %s", file.getName(), patterns.stream()
                    .sorted(Comparator.comparingInt(Pattern::getPriority).reversed())
                    .filter(pattern -> {                                            // Streaming the patterns
                                                                                    // based on priority
                        boolean found = false;                                      // and checking if we can find them
                        try {                                                       // in the file,
                            found = RabinKarpWorker.run(Files.readString(file.toPath()), pattern.getPattern());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return found;                                               // then returning true if we found
                    })
                            .map(Pattern::getResult)                                // If it finds a pattern in the file,
                            .findFirst()                                            // the app saves the result
                                                                                    // of that pattern and stops.
                            .orElse("Unknown file type"));                          // If it doesn't find a pattern
                                                                                    // then it saves "Unknown file type"
            filesList.forEach(f -> callables.add(() -> result));
            }
        executor.invokeAll(callables);                                              // Staring all the jobs
        callables.forEach(callable -> {                                             // And for each job we print
            try {                                                                   // the result saved in it
                System.out.println(callable.call());
            } catch (Exception e) {
                e.printStackTrace();                                                // Catching errors in the jobs.
            }
        });
        executor.shutdown();                                                        // shutting down the executor.
    }
}

