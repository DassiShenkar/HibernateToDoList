package il.ac.shenkar.hibernate;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class Time {
    private long startTime;
    private long endTime;
    private static Time instance = null;

    public static Time getInstance() {
        if (instance == null) {
            instance = new Time();
        }
        return instance;
    }

    private Time(){
        startTime = 0;
    }

    public void startCount(){
        startTime = System.nanoTime();
    }
    public double computeTime(){
        endTime = System.nanoTime();
        long result = endTime - startTime;
        double seconds = (double)result / 1000000000.0;
        seconds = (double) Math.round(seconds * 1000) / 1000; // leave only 3 digits after dot
        return seconds;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public static void setInstance(Time instance) {
        Time.instance = instance;
    }
}
