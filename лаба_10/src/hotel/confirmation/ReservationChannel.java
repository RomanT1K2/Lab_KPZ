package hotel.confirmation;

import hotel.booking.Booking;
import hotel.common.Log;

/**
 * Creator. confirm() — єдиний шаблонний процес (final), описаний ОДИН раз:
 * перевірити → зафіксувати → сформувати → надіслати.
 * Вид підтвердження визначає factory method createConfirmation() у підкласі-каналі.
 */
public abstract class ReservationChannel {
    private final RoomInventory inventory;

    protected ReservationChannel(RoomInventory inventory) { this.inventory = inventory; }

    public abstract String name();
    protected abstract Confirmation createConfirmation(Booking booking);   // Factory Method

    public final boolean confirm(Booking booking) {
        Log.event(name(), "Підтвердження бронювання №" + booking.id());
        if (!inventory.isAvailable(booking.category())) {
            Log.event(name(), "Немає вільних номерів категорії «" + booking.category().title() + "»");
            return false;
        }
        Log.event(name(), "Номер категорії «" + booking.category().title() + "» доступний");
        inventory.reserve(booking.category());
        Log.event(name(), "Бронювання зафіксовано (залишилось номерів: " + inventory.left(booking.category()) + ")");
        Confirmation confirmation = createConfirmation(booking);
        Log.event(name(), "Сформовано підтвердження:\n" + confirmation.render());
        confirmation.send();
        return true;
    }
}
