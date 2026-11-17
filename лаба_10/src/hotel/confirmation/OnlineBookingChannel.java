package hotel.confirmation;

import hotel.booking.Booking;

/** Новий канал «онлайн-бронювання»: додається новими класами, ReservationChannel не змінюється. */
public final class OnlineBookingChannel extends ReservationChannel {
    public OnlineBookingChannel(RoomInventory inventory) { super(inventory); }
    @Override public String name() { return "Онлайн-бронювання"; }
    @Override protected Confirmation createConfirmation(Booking b) { return new OnlineBookingConfirmation(b, name()); }
}
