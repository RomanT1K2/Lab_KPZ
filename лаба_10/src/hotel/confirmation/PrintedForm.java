package hotel.confirmation;

import hotel.booking.Booking;
import hotel.common.Log;

/** Concrete Product: бланк для друку. */
public final class PrintedForm implements Confirmation {
    private final Booking booking;
    private final String source;

    public PrintedForm(Booking booking, String source) { this.booking = booking; this.source = source; }

    @Override public String render() {
        return "    ┌─ БЛАНК ПІДТВЕРДЖЕННЯ №" + booking.id() + " ─┐\n"
             + "    │ Гість: " + booking.guest().name() + "\n"
             + "    │ " + booking.checkIn() + " – " + booking.checkOut() + ", номер «" + booking.category().title() + "»\n"
             + "    └─ підпис адміністратора: ________ ─┘";
    }
    @Override public void send() { Log.event(source, "Бланк надруковано й виданий гостю"); }
}
