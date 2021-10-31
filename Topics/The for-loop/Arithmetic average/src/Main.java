import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner var = new Scanner(System.in);

        int a = var.nextInt();
        int b = var.nextInt();
        float sum = 0f;
        int num = 0;

        final int divisibleBy = 3;

        for (; a <= b; a++) {
            if (a % divisibleBy == 0) {
                sum += a;
                num++;
            }
        }

        System.out.println(sum / num);
    }
}