package analyzer;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    /**
     * The main function. Takes 3 arguments
     * ( 1.Folder - Folder with files to check)
     *   2.Pattern - the pattern to search for.
     *   3.Result - the string you want to be printed if the pattern is found in the file)
     *
     * the app will run a multithreaded work (each file as a thread)
     * and execute the KMP string search algorithm
     */
    public static void main(String[] args) throws InterruptedException {

        final int argNum = 3;                                                       // the amount of arguments needed

        if (args.length != argNum) {                                                // if the amount of arguments
            System.out.printf("invalid amount of arguments.\n" +                    // provided is not sufficient,
                    "Please add %d operators,\n" +                                  // the app will print a message
                    "a path to a folder from which to take the files" +             // and exit with an error.
                    "a pattern to search in the files" +
                    "and a result to print if the pattern is found", argNum);
            System.exit(1);
        }

        ArrayList<File> filesArray = new ArrayList<File>();                         // creating a fileArray to hold all
                                                                                    // the files in the directory
        String folder = args[0];
        String pattern = args[1];
        String result = args[2];

        int poolSize = Runtime.getRuntime().availableProcessors();                  // getting a pool size based of the
                                                                                    // amount of available processors

        new FileExtractor().run(folder, filesArray);                                // extracts the files

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);          // creating a Executor
                                                                                    // based on the pool size

        for (File file : filesArray) {                                              // submit all the files
                                                                                    // in the array
            try {                                                                   // to the executor by creating
                executor.submit(new KmpWorker(file, pattern, result));              // a new KMP worker
            } catch (Exception e) {
                e.printStackTrace();                                                // catching the exceptions
                                                                                    // from the threads
            }
        }

        executor.shutdown();

        boolean finished = executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}

