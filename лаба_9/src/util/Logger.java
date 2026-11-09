package util;

/** Простий журнал подій у форматі [час] [джерело] подія. Час умовний. */
public class Logger {
    private static int minutes = 9 * 60; // умовний час починається з 09:00

    public static void log(String source, String event) {
        System.out.printf("[%02d:%02d] [%s] %s%n", minutes / 60, minutes % 60, source, event);
        minutes++; // кожна подія "займає" одну умовну хвилину
    }

    /** Емуляція тривалої операції. */
    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
