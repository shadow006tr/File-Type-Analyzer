package analyzer;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        final int minArgs = 3;

        if (args.length != minArgs) {
            System.out.println("invalid amount of arguments.\n" +
                    "Please add an operator (\"+\", \"-\", \"*\")," +
                    " and two integers as the command-line arguments");
            System.exit(1);
        }

        String filePath = args[0];
        String pattern = args[1];
        String result = args[2];

        int byteAsInt;
        int count = 0;

        boolean found = false;

        try (InputStream inputStream = new FileInputStream(filePath)) {

            byteAsInt = inputStream.read();


            while (byteAsInt != -1 && !found) {
                if (byteAsInt == pattern.charAt(count)) {
                    count++;
                    if (count == pattern.length()) {
                        found = true;
                        System.out.println(result);
                    }
                } else {
                    count = 0;
                    if (byteAsInt == pattern.charAt(count)) {
                        count++;
                    }
                }
                byteAsInt = inputStream.read();
            }

            if (!found) {
                System.out.println("Unknown file type");
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }




    }
}
