class ManufacturingController {

    static int numberOfProducts = 0;

    public static String requestProduct(String product) {
        numberOfProducts += 1;
        return getNumberOfProducts() + ". Requested " + product;
    }

    public static int getNumberOfProducts() {
        return numberOfProducts;
    }
}