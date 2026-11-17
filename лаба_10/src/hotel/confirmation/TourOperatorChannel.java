package hotel.confirmation;

import hotel.booking.Booking;

/** Concrete Creator: туроператор → файл для його системи. */
public final class TourOperatorChannel extends ReservationChannel {
    public TourOperatorChannel(RoomInventory inventory) { super(inventory); }
    @Override public String name() { return "Туроператор"; }
    @Override protected Confirmation createConfirmation(Booking b) { return new TourOperatorFile(b, name()); }
}
