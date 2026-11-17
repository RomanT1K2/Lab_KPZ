package hotel.booking;

import java.time.LocalDate;

/** Director: типові послідовності кроків описані в ОДНОМУ місці. */
public final class BookingDirector {

    /** «Вікенд»: стандарт, п'ятниця → неділя, сніданки. */
    public Booking weekend(Booking.Builder builder, Guest guest, LocalDate friday)
            throws BookingValidationException {
        return builder.guest(guest)
                .category(RoomCategory.STANDARD)
                .checkIn(friday)
                .checkOut(friday.plusDays(2))
                .withBreakfast()
                .comment("Типове бронювання «Вікенд»")
                .build();
    }

    /** «Лижний тиждень»: люкс, 7 ночей, сніданки, трансфер, пізній виїзд. */
    public Booking skiWeek(Booking.Builder builder, Guest guest, LocalDate start)
            throws BookingValidationException {
        return builder.guest(guest)
                .category(RoomCategory.LUXURY)
                .checkIn(start)
                .checkOut(start.plusDays(7))
                .withBreakfast()
                .withTransfer()
                .withLateCheckout()
                .comment("Типове бронювання «Лижний тиждень»")
                .build();
    }
}
