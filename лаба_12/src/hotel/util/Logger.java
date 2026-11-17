package hotel.util;

/**
 * Простий журнал подій у форматі: [час] [джерело] подія.
 * Час умовний: кожна подія "додає" 1 хвилину до годинника.
 */
public class Logger {

    // умовний годинник: хвилини від 08:00
    private static int minutes = 8 * 60;

    public static void log(String source, String event) {
        String time = String.format("%02d:%02d", minutes / 60, minutes % 60);
        System.out.println("[" + time + "] [" + source + "] " + event);
        minutes++; // наступна подія відбудеться на хвилину пізніше
    }
}
