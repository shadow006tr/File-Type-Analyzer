import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        float m = scanner.nextInt();
        float p = scanner.nextInt();
        float k = scanner.nextInt();
        int i = 0;

        final int one = 1;
        final int hundered = 100;

        while (m < k) {
            m *= one + p / hundered;
            i++;
        }
        System.out.println(i);

    }
}