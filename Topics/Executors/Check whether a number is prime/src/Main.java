import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newCachedThreadPool();


        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            try {
                executor.submit(new PrintIfPrimeTask(number));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();
    }
}

class PrintIfPrimeTask implements Runnable {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        final int minNumber = 2;

        if (number == 1) {
            return;
        }

        boolean isPrime = true;
        int i = minNumber;
        while ((double)i <= Math.sqrt(number)) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
            i++;
        }
        if (isPrime) {
            System.out.println(number);
        }
    }
}