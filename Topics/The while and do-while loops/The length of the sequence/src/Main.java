import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int i = 0;
        int inputNumber = 0;
        Scanner input = new Scanner(System.in);
        do {
            inputNumber = input.nextInt();
            if (inputNumber != 0) {
                i++;
            }
        } while (inputNumber != 0);
        System.out.println(i);
    }
}