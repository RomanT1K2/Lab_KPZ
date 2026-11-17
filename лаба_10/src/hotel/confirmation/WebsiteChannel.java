package hotel.confirmation;

import hotel.booking.Booking;

/** Concrete Creator: сайт → лист. */
public final class WebsiteChannel extends ReservationChannel {
    public WebsiteChannel(RoomInventory inventory) { super(inventory); }
    @Override public String name() { return "Сайт"; }
    @Override protected Confirmation createConfirmation(Booking b) { return new EmailConfirmation(b, name()); }
}
