package analyzer;

public class Pattern {

    /**
     * A class to create Patter objects.
     * the object holds int Priority,
     * string Pattern and string Result.
     *
     * These objects are created by the PatternExtractor
     * from the pattern database, and are used in the main function
     * to be sent to RabinKarpWorkers as jobs.
     *
     * All the object's variables are private,
     * use getters to get them.
     */

    private final int priority;
    private final String pattern;
    private final String result;

    public Pattern(int priority, String pattern, String result) {                       // constructor for objects
        this.priority = priority;
        this.pattern = pattern;
        this.result = result;
    }

    public int getPriority() {                                                          // getters for the variables
        return priority;
    }

    public String getPattern() {
        return pattern;
    }

    public String getResult() {
        return result;
    }
}
