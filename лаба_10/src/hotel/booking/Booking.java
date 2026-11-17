package hotel.booking;

import hotel.common.Log;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Product. Незмінний: усі поля final, конструктор приватний —
 * створити Booking можна тільки через Booking.Builder.build(), який перевіряє коректність.
 */
public final class Booking {
    private static int counter = 0;               // статичний лічильник номерів

    private final int id;
    private final Guest guest;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final RoomCategory category;
    private final boolean breakfast;
    private final boolean transfer;
    private final boolean lateCheckout;
    private final boolean babyCot;
    private final String comment;

    private Booking(Builder b) {
        this.id = ++counter;
        this.guest = b.guest;
        this.checkIn = b.checkIn;
        this.checkOut = b.checkOut;
        this.category = b.category;
        this.breakfast = b.breakfast;
        this.transfer = b.transfer;
        this.lateCheckout = b.lateCheckout;
        this.babyCot = b.babyCot;
        this.comment = b.comment;
    }

    public static Builder builder() { return new Builder(); }

    public int id() { return id; }
    public Guest guest() { return guest; }
    public LocalDate checkIn() { return checkIn; }
    public LocalDate checkOut() { return checkOut; }
    public RoomCategory category() { return category; }
    public long nights() { return ChronoUnit.DAYS.between(checkIn, checkOut); }
    public boolean breakfast() { return breakfast; }
    public boolean transfer() { return transfer; }
    public boolean lateCheckout() { return lateCheckout; }
    public boolean babyCot() { return babyCot; }
    public String comment() { return comment; }

    @Override public String toString() {
        StringBuilder wishes = new StringBuilder();
        if (breakfast) wishes.append("сніданки, ");
        if (transfer) wishes.append("трансфер, ");
        if (lateCheckout) wishes.append("пізній виїзд, ");
        if (babyCot) wishes.append("дитяче ліжко, ");
        String w = wishes.length() == 0 ? "—" : wishes.substring(0, wishes.length() - 2);
        return "Бронювання №" + id + "\n"
             + "    гість:      " + guest + "\n"
             + "    проживання: " + checkIn + " → " + checkOut + " (" + nights() + " ноч.)\n"
             + "    номер:      " + category.title() + "\n"
             + "    побажання:  " + w + "\n"
             + "    коментар:   " + (comment.isEmpty() ? "—" : comment);
    }

    /** Builder: кожна частина — окрема дія; перевірка лише в build(). */
    public static final class Builder {
        private Guest guest;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private RoomCategory category;
        private boolean breakfast, transfer, lateCheckout, babyCot;
        private String comment = "";

        private Builder() { }

        public Builder guest(Guest guest) { this.guest = guest; return this; }
        public Builder checkIn(LocalDate date) { this.checkIn = date; return this; }
        public Builder checkOut(LocalDate date) { this.checkOut = date; return this; }
        public Builder category(RoomCategory category) { this.category = category; return this; }
        public Builder withBreakfast() { this.breakfast = true; return this; }
        public Builder withTransfer() { this.transfer = true; return this; }
        public Builder withLateCheckout() { this.lateCheckout = true; return this; }
        public Builder withBabyCot() { this.babyCot = true; return this; }
        public Builder comment(String text) { this.comment = text == null ? "" : text; return this; }

        public Booking build() throws BookingValidationException {
            if (guest == null) throw new BookingValidationException("не вказано гостя");
            if (category == null) throw new BookingValidationException("не вказано категорію номера");
            if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
                throw new BookingValidationException("некоректні дати проживання");
            }
            Booking booking = new Booking(this);
            Log.event("Бронювання", "Сформовано бронювання №" + booking.id());
            return booking;
        }
    }
}
