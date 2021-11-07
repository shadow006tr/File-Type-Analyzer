package analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class FileExtractor {

    /**
     * A method to extract all the files from the folder provided.
     */

    public void run (String folder, ArrayList<File> filesArray) {

        File currentFolder = new File(folder);                                      // Making a file object for
        // the current folder

        File[] files = currentFolder.listFiles();                                   // Getting all the files form
        // the folder as file array.

        if (files != null) {                                                        // If we managed to get the files,
            for(File file : files) {                                                // we go on them one by one.
                if (file.isFile()) {                                                // Adding them to the array
                    filesArray.add(file);                                           // if they are a file,
                } else if (Objects.requireNonNull(file.list()).length > 0) {        // and running on then with
                    run(file.getAbsolutePath(), filesArray);                        // run() if they are
                }                                                                   // a non-empty folder.
            }
        }
    }
}
