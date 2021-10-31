import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = 0;
        int charAsNumber;
        boolean lastWasSpace = false;

        charAsNumber = reader.read();

        while (charAsNumber == ' ') {
            charAsNumber = reader.read();
        }

        if (charAsNumber != -1) {
            count = 1;
        }

        while (charAsNumber != -1) {

            char character = (char) charAsNumber;
            if (character == ' ' && !lastWasSpace) {
                count++;
                lastWasSpace = true;
            } else if (character != ' ' && lastWasSpace) {
                lastWasSpace = false;
            }
            charAsNumber = reader.read();
        }

        System.out.println(count);
        reader.close();
    }
}