package hotel.chain;

/** Типи запитів гостя. */
public enum RequestType {
    TOWELS("рушники"),
    ALARM("будильник"),
    CLEANING("прибирання"),
    MALFUNCTION("несправність"),
    COMPLAINT("скарга"),
    COMPENSATION("компенсація"),
    OTHER("нестандартний запит");

    private final String title;

    RequestType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
