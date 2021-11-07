import java.util.Scanner;

class NumbersFilter extends Thread {

    /* use it to read numbers from the standard input */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        int number;
        do {
            number = scanner.nextInt();
            if (number % 2 == 0 && number != 0) {
                System.out.println(number);
            }
        } while (number != 0);
    }
}