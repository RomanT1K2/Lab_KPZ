package hotel.common;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Єдине місце виводу подій у форматі: [час] [джерело] подія. Час умовний (+7 с на подію). */
public final class Log {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static LocalTime clock = LocalTime.of(9, 0, 0);

    private Log() { }

    public static void event(String source, String message) {
        clock = clock.plusSeconds(7);
        System.out.printf("[%s] [%s] %s%n", clock.format(FMT), source, message);
    }
}
