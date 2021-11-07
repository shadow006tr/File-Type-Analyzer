class CounterThread extends Thread {

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(1L);
            }
        } catch (InterruptedException e) {
            System.out.println("It was interrupted");
        }
    }
}
