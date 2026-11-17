package hotel.confirmation;

import hotel.booking.Booking;

/** Concrete Creator: рецепція → друкований бланк. */
public final class ReceptionChannel extends ReservationChannel {
    public ReceptionChannel(RoomInventory inventory) { super(inventory); }
    @Override public String name() { return "Рецепція"; }
    @Override protected Confirmation createConfirmation(Booking b) { return new PrintedForm(b, name()); }
}
