
interface Flying {
    // returns height of flying in meters
    int getHeight();

    // implements a default method getHeightInKm that returns height of flying in km as int type
    default int getHeightInKm() {

        final int kmInMeters = 1000;
        return  getHeight() / kmInMeters;
    }
}