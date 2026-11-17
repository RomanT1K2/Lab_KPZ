package hotel.booking;

/** Бронювання не пройшло перевірку на завершальному кроці. */
public class BookingValidationException extends Exception {
    public BookingValidationException(String message) { super(message); }
}
