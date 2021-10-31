
class Car {

    final int speedChange = 5;

    int yearModel;
    String make;
    int speed;

    public void accelerate() {
        this.speed += speedChange;
    }

    public void brake() {
        this.speed = this.speed > speedChange ? this.speed - speedChange : 0;
    }
}