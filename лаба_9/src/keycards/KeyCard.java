package keycards;

/** Електронна ключ-карта (об'єкт, який живе в пулі). */
public class KeyCard {
    private static final int NO_ROOM = 0;

    private final String id;
    private int roomNumber = NO_ROOM;
    private String validUntil = null;
    private int issueCount = 0;

    public KeyCard(String id) {
        this.id = id;
    }

    /** Кодування карти під номер. */
    public void program(int roomNumber, String validUntil) {
        this.roomNumber = roomNumber;
        this.validUntil = validUntil;
        this.issueCount++;
    }

    /** Стирання доступу й терміну дії. Повертає номер, до якого був доступ. */
    public int erase() {
        int oldRoom = roomNumber;
        roomNumber = NO_ROOM;
        validUntil = null;
        return oldRoom;
    }

    public boolean opens(int room) {
        return roomNumber != NO_ROOM && roomNumber == room;
    }

    public String getId() {
        return id;
    }

    public int getIssueCount() {
        return issueCount;
    }
}
