import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> array = new ArrayList<>();
        int numberToFind;

        while (scanner.hasNextInt()) {
            array.add(scanner.nextInt());
        }
        if (array.isEmpty()) {
            System.exit(1);
        }

        numberToFind = array.get(array.size() - 1);
        array.remove(array.size() - 1);

        ArrayList<Integer> results = findNearest(array, numberToFind);

        for (int item: results) {
            System.out.print(item + " ");
        }

    }

    private static ArrayList<Integer> findNearest(ArrayList<Integer> array, int numberToFind) {
        ArrayList<Integer> results = new ArrayList<>();
        int minDistance = Math.abs(numberToFind - array.get(0));
        for (int item: array) {
            if (Math.abs(numberToFind - item) > minDistance) {
                continue;
            } else if (Math.abs(numberToFind - item) < minDistance) {
                results.clear();
                minDistance = Math.abs(numberToFind - item);
            }
            results.add(item);
        }
        Collections.sort(results);
        return results;
    }
}