package hotel.housekeeping;

/** Номер готелю. */
public final class Room {
    private final String number;
    private final String category;
    private RoomKit kit;

    public Room(String number, String category) {
        this.number = number;
        this.category = category;
    }

    public String number() { return number; }
    public String category() { return category; }
    public RoomKit kit() { return kit; }
    void assign(RoomKit kit) { this.kit = kit; }
}
