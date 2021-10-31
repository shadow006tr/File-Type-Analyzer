import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner var = new Scanner(System.in);

        int a = var.nextInt();
        int b = var.nextInt();

        long product = 1;

        for (; a < b; a++) {
            product *= a;
        }

        System.out.println(product);
    }
}