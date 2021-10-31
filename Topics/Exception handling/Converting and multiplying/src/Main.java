import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        final int multiplyBy = 10;
        final String zero = "0";

        Scanner scanner = new Scanner(System.in);
        String str;

        do {
            str = scanner.next();
            try {
                if (!str.equals(zero)) {
                    System.out.println(Integer.parseInt(str) * multiplyBy);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid user input: " + str);
            }

        } while (!str.equals(zero));
    }
}