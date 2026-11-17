package hotel.flyweight;

import java.time.LocalDate;

/**
 * Запис календаря: ЗОВНІШНІ (унікальні) дані + посилання на легковаговик.
 * Тут немає ні опису, ні фото, ні зручностей - вони лежать у RoomCategory.
 */
public class CalendarEntry {

    private final int roomNumber;          // унікальне
    private final LocalDate date;          // унікальне
    private RoomStatus status;             // унікальне
    private final RoomCategory category;   // спільне (посилання на вн об'єкт, не копія!)

    public CalendarEntry(int roomNumber, LocalDate date, RoomStatus status, RoomCategory category) {
        this.roomNumber = roomNumber;
        this.date = date;
        this.status = status;
        this.category = category;
    }

    public int getRoomNumber() { return roomNumber; }
    public LocalDate getDate() { return date; }
    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }
    public RoomCategory getCategory() { return category; }

    @Override
    public String toString() {
        return "Номер " + roomNumber + ", " + date + ", " + status + ", категорія: " + category.getName();
    }
}
