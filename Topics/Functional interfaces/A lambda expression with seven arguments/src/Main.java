class Seven {
    public static SeptenaryStringFunction fun = s -> String.join("", s).toUpperCase();
}

@FunctionalInterface
interface SeptenaryStringFunction {
    String apply(String... s);
}