package hotel.chain;

/** Запит гостя: номер, тип і короткий опис. */
public class GuestRequest {
    private final int room;
    private final RequestType type;
    private final String description;

    public GuestRequest(int room, RequestType type, String description) {
        this.room = room;
        this.type = type;
        this.description = description;
    }

    public int getRoom() { return room; }
    public RequestType getType() { return type; }

    @Override
    public String toString() {
        return "запит «" + type.getTitle() + "» (номер " + room + ": " + description + ")";
    }
}
