import java.util.*;

public class Main {

    private static String array = null;

    public static void methodCatchingSomeExceptions() {
        try {
            methodThrowingExceptions();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException");
        }
    }

    /* Do not change code below */
    public static void methodThrowingExceptions() {
        final int zero = 0;
        final int one = 1;
        final int two = 2;
        final int three = 3;

        if (array == null) {
            return;
        }
        int[] integers = Arrays.stream(array.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Object someValue = integers[integers[zero]];
        if (integers[zero] + integers[one] == integers[two]) {
            integers = null;
        }
        int sum = 0;
        for (int i : integers) {
            sum += i;
        }
        int meanValue = integers.length / sum;
        if (integers[two] == integers[three]) {
            String string = (String) someValue;
            System.out.print("Random value is " + string);
        }
        System.out.print("Mean is " + meanValue);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        array = scanner.nextLine();
        try {
            methodCatchingSomeExceptions();
        } catch (Exception e) {
            System.out.println("Caught: " + e.getClass().getName());
        }
    }
}