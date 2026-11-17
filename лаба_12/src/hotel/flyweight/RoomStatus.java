package hotel.flyweight;

/** Стан номера на конкретну ніч. */
public enum RoomStatus {
    FREE("вільний"),
    BOOKED("заброньований"),
    OCCUPIED("зайнятий");

    private final String title;

    RoomStatus(String title) { this.title = title; }

    @Override
    public String toString() { return title; }
}
