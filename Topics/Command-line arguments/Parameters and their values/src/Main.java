
class Problem {
    public static void main(String[] args) {

        boolean isParameter = true;

        for (String arg : args) {

            if (isParameter) {
                System.out.print(arg + "=");
                isParameter = false;
            } else {
                System.out.println(arg);
                isParameter = true;
            }
        }
    }
}