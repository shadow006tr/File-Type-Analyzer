
class Problem {
    public static void main(String[] args) {

        final String modeStr = "mode";

        String mode = "default";

        boolean isParameter = true;
        boolean isMode = false;
        boolean modeFound = false;

        for (String arg : args) {

            if (isParameter) {
                if (arg.equals(modeStr)) {
                    isMode = true;
                    modeFound = true;
                }
                isParameter = false;

            } else {

                if (isMode) {
                    System.out.println(arg);
                    isMode = false;
                }
                isParameter = true;
            }
        }

        if (!modeFound) {
            System.out.println(mode);
        }
    }
}