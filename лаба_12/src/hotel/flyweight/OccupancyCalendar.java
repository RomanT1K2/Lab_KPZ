package hotel.flyweight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Календар зайнятості: містить усі записи (номер x ніч). */
public class OccupancyCalendar {

    private static final String[] CATEGORY_NAMES = {"Стандарт", "Комфорт", "Люкс", "Сімейний"};

    private final List<CalendarEntry> entries = new ArrayList<>();

    /**
     * Формує календар. Категорія номера визначається за його номером:
     * 4 категорії чергуються по колу.
     */
    public void build(int roomsCount, int nights, LocalDate startDate) {
        for (int i = 0; i < roomsCount; i++) {
            int roomNumber = 101 + i;
            // беремо категорію з ФАБРИКИ - нових об'єктів вона не створює, якщо категорія вже є
            RoomCategory category = RoomCategoryFactory.getCategory(CATEGORY_NAMES[i % 4]);

            for (int d = 0; d < nights; d++) {
                RoomStatus status = RoomStatus.FREE;
                if (d % 7 == 0) status = RoomStatus.BOOKED;
                if (d % 11 == 0) status = RoomStatus.OCCUPIED;
                entries.add(new CalendarEntry(roomNumber, startDate.plusDays(d), status, category));
            }
        }
    }

    public int getEntriesCount() { return entries.size(); }

    /** Знаходить запис за номером кімнати і датою. */
    public CalendarEntry find(int roomNumber, LocalDate date) {
        for (CalendarEntry e : entries) {
            if (e.getRoomNumber() == roomNumber && e.getDate().equals(date)) {
                return e;
            }
        }
        return null;
    }
}
