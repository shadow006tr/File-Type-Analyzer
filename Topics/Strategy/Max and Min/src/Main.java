import java.util.Arrays;
import java.util.Scanner;

class Finder {

    private final FindingStrategy strategy;

    public Finder(FindingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * It performs the search algorithm according to the given strategy
     */
    public int find(int[] numbers) {
        return this.strategy.getResult(numbers);
    }
}

interface FindingStrategy {

    /**
     * Returns search result
     */
    int getResult(int[] numbers);

}

class MaxFindingStrategy implements FindingStrategy {
    //finds a max number if the array is not empty' otherwise returns Integer.MIN_VALUE
    public int getResult(int[] numbers) {
        return Arrays.stream(numbers).max().isPresent() ? Arrays.stream(numbers).max().getAsInt() : Integer.MIN_VALUE;
    }
}

class MinFindingStrategy implements FindingStrategy {
    //finds a min number if the array is not empty' otherwise returns Integer.MAX_VALUE
    public int getResult(int[] numbers) {
        return Arrays.stream(numbers).min().isPresent() ? Arrays.stream(numbers).min().getAsInt() : Integer.MAX_VALUE;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        final String[] elements = scanner.nextLine().split("\\s+");
        int[] numbers = null;

        if ("EMPTY".equals(elements[0])) {
            numbers = new int[0];   
        } else {
            numbers = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                numbers[i] = Integer.parseInt(elements[i]);
            }
        }

        final String type = scanner.nextLine();

        Finder finder = null;

        switch (type) {
            case "MIN":
                finder = new Finder(new MinFindingStrategy());
                break;
            case "MAX":
                finder = new Finder(new MaxFindingStrategy());
                break;
            default:
                break;
        }

        if (finder == null) {
            throw new RuntimeException(
                    "Unknown strategy type passed. Please, write to the author of the problem.");
        }

        System.out.println(finder.find(numbers));
    }
}