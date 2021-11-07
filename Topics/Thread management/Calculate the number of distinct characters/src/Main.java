import java.util.Scanner;
import java.util.Arrays;

public class Main {

    private static final long MAIN_THREAD_ID = Thread.currentThread().getId();

    // Fix this method
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        SlowStringProcessor processor = new SlowStringProcessor(str);

        processor.start();
        processor.join();

        System.out.println(processor.getNumberOfUniqueCharacters());
    }

    //Don't change the code below
    static class SlowStringProcessor extends Thread {

        private final String s;
        private final int sleepTime = 2000;
        private volatile long numberOfUniqueCharacters = 0;

        public SlowStringProcessor(String s) {
            super();
            this.s = s;
        }

        @Override
        public void run() {

            final long currentId = Thread.currentThread().getId();

            if (currentId == MAIN_THREAD_ID) {
                throw new RuntimeException("You must start a new thread!");
            }

            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                throw new RuntimeException("Do not interrupt the processor", e);
            }

            this.numberOfUniqueCharacters = Arrays.stream(s.split("")).distinct().count();
        }

        public long getNumberOfUniqueCharacters() {
            return numberOfUniqueCharacters;
        }
    }
}