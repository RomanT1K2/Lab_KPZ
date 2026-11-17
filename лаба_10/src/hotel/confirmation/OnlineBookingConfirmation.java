package hotel.confirmation;

import hotel.booking.Booking;
import hotel.common.Log;

/** Concrete Product: JSON-відповідь для сервісу онлайн-бронювання. */
public final class OnlineBookingConfirmation implements Confirmation {
    private final Booking booking;
    private final String source;

    public OnlineBookingConfirmation(Booking booking, String source) { this.booking = booking; this.source = source; }

    @Override public String render() {
        return "    {\"id\": " + booking.id() + ", \"guest\": \"" + booking.guest().name()
             + "\", \"checkIn\": \"" + booking.checkIn() + "\", \"checkOut\": \"" + booking.checkOut()
             + "\", \"category\": \"" + booking.category() + "\", \"status\": \"CONFIRMED\"}";
    }
    @Override public void send() { Log.event(source, "JSON-підтвердження відправлено через API сервісу онлайн-бронювання"); }
}
