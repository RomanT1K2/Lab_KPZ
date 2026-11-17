package hotel.booking;

/** Категорії номерів для бронювання. */
public enum RoomCategory {
    STANDARD("стандарт"), LUXURY("люкс"), FAMILY("сімейний");

    private final String title;
    RoomCategory(String title) { this.title = title; }
    public String title() { return title; }
}
