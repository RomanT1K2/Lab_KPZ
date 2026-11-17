// Єдиний спосіб виводу подій у форматі: [час] [джерело] подія
// Час умовний: кожна подія "триває" 1 хвилину, починаємо з 09:00
public class Logger {
    private static int minute = 9 * 60;

    public static void log(String source, String event) {
        System.out.printf("[%02d:%02d] [%s] %s%n", minute / 60, minute % 60, source, event);
        minute++;
    }
}
