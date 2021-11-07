class InterruptedExample {

    private static final long MAIN_THREAD_ID = Thread.currentThread().getId();

    public static void main(String[] args) throws InterruptedException {
        final long sleepTime = 2500L;
        Worker worker = new Worker();
        worker.start();
        Thread.sleep(sleepTime);
        worker.interrupt();

        // write your code here
    }

    // Don't change the code below
    static class Worker extends Thread {

        @Override
        public void run() {
            final long sleepTime = 1000L;

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException("You need to wait longer!", e);
            }

            final long currentId = Thread.currentThread().getId();

            if (currentId == MAIN_THREAD_ID) {
                throw new RuntimeException("You must start a new thread!");
            }

            while (true) {
                if (isInterrupted()) {
                    System.out.println("Interrupted");
                    break;
                }
            }
        }
    }
}