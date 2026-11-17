package hotel.common;

/**
 * Простий журнал подій. Формат рядка: [час] [джерело] подія.
 * Час умовний: починається з 08:00 і збільшується на 1 хвилину з кожним записом.
 */
public class Journal {
    private static int minutes = 8 * 60; // поточний умовний час у хвилинах

    // Встановити час вручну (наприклад, для нічної зміни)
    public static void setTime(int hour, int minute) {
        minutes = hour * 60 + minute;
    }

    // Поточний час у вигляді "ГГ:ХХ" (без зсуву годинника)
    public static String now() {
        return String.format("%02d:%02d", (minutes / 60) % 24, minutes % 60);
    }

    // Записати подію в консоль і зсунути час на хвилину
    public static void log(String source, String event) {
        System.out.println("[" + now() + "] [" + source + "] " + event);
        minutes++;
    }
}
