
class Clock {


    final int maxMinutes = 59;
    final int maxHour = 12;
    final int firstHour = 1;
    final int firstMinute = 0;

    int hours = maxHour;
    int minutes = firstMinute;

    void next() {
        if (minutes < maxMinutes) {
            minutes++;
        } else {
            minutes = firstMinute;
            if (hours < maxHour) {
                hours++;
            } else {
                hours = firstHour;
            }
        }
    }
}