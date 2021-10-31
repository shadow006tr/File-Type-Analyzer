import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner var = new Scanner(System.in);

        int a = var.nextInt();
        int b = var.nextInt();
        int c = var.nextInt();
        int d = var.nextInt();

        final int start = 0;
        final int end = 1000;
        final int cubed = 3;
        final int squared = 2;

        for (int x = start; x <= end; x++) {
            if (a * Math.pow(x, cubed) + b * Math.pow(x, squared) + c * x + d == 0) {
                System.out.println(x);
            }
        }
    }
}