
class Problem {
    public static void main(String[] args) {

        final String add = "+";
        final String subtract = "-";
        final String multiply = "*";

        final int minArgs = 3;
        final int operator = 0;
        final int int1 = 1;
        final int int2 = 2;

        if (args.length != minArgs) {
            System.out.println("invalid amount of arguments.\n" +
                    "Please add an operator (\"+\", \"-\", \"*\")," +
                    " and two integers as the command-line arguments");
            System.exit(1);
        }

        switch (args[operator]) {
            case add:
                System.out.println(Integer.parseInt(args[int1]) + Integer.parseInt(args[int2]));
                break;
            case subtract:
                System.out.println(Integer.parseInt(args[int1]) - Integer.parseInt(args[int2]));
                break;
            case multiply:
                System.out.println(Integer.parseInt(args[int1]) * Integer.parseInt(args[int2]));
                break;
            default:
                System.out.println("Unknown operator");
                break;
        }


    }
}