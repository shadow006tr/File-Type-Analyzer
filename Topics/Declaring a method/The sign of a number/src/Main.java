import java.util.Scanner;

public class Main {

    public static int sign(int number) {
        int result = 0;

        if (number < 0) {
            result -= 1;
        } else if (number > 0) {
            result += 1;
        }

        return result;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        System.out.println(sign(n));
    }
}