package hotel.confirmation;

import hotel.booking.Booking;
import hotel.common.Log;

/** Concrete Product: електронний лист. */
public final class EmailConfirmation implements Confirmation {
    private final Booking booking;
    private final String source;

    public EmailConfirmation(Booking booking, String source) { this.booking = booking; this.source = source; }

    @Override public String render() {
        return "    To: " + booking.guest().contact() + "\n"
             + "    Subject: Підтвердження бронювання №" + booking.id() + "\n"
             + "    Вітаємо, " + booking.guest().name() + "! Чекаємо вас " + booking.checkIn()
             + " у категорії «" + booking.category().title() + "».";
    }
    @Override public void send() { Log.event(source, "Лист надіслано на " + booking.guest().contact()); }
}
