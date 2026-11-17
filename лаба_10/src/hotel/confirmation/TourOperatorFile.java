package hotel.confirmation;

import hotel.booking.Booking;
import hotel.common.Log;

/** Concrete Product: CSV-файл для системи туроператора. */
public final class TourOperatorFile implements Confirmation {
    private final Booking booking;
    private final String source;

    public TourOperatorFile(Booking booking, String source) { this.booking = booking; this.source = source; }

    @Override public String render() {
        return "    booking_" + booking.id() + ".csv\n"
             + "    id;guest;check_in;check_out;category\n"
             + "    " + booking.id() + ";" + booking.guest().name() + ";" + booking.checkIn() + ";"
             + booking.checkOut() + ";" + booking.category();
    }
    @Override public void send() { Log.event(source, "Файл booking_" + booking.id() + ".csv передано в систему туроператора"); }
}
