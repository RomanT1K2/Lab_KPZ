// ===== ЗАВДАННЯ 2: Заселення гостя -> шаблон FACADE =====
// Чому Facade: складну послідовність викликів багатьох підсистем
// ховаємо за однією простою операцією checkIn().

// ---- Підсистеми (залишаються доступними окремо) ----
class BookingService {
    boolean verify(String bookingId) {
        Logger.log("BookingService", "бронювання " + bookingId + " перевірено");
        return true;
    }
}

class GuestRegistry {
    void register(String guest) {
        Logger.log("GuestRegistry", "гостя " + guest + " зареєстровано");
    }
}

class RoomAssignment {
    int assign(String bookingId) {
        Logger.log("RoomAssignment", "за бронюванням " + bookingId + " призначено номер 305");
        return 305;
    }
}

class KeyCardService {
    void issue(int room, String guest) {
        Logger.log("KeyCardService", "видано ключ-карту від номера " + room + " для " + guest);
    }
    void reissue(int room, String guest) {
        Logger.log("KeyCardService", "ПЕРЕВИПУЩЕНО ключ-карту від номера " + room + " для " + guest);
    }
}

class BillingAccount {
    void open(String guest, int room) {
        Logger.log("BillingAccount", "відкрито рахунок гостя " + guest + " (номер " + room + ")");
    }
}

class HousekeepingService {
    void notifyRoom(int room) {
        Logger.log("HousekeepingService", "покоївок повідомлено про заселення в номер " + room);
    }
}

// ---- Фасад: уся послідовність описана ТІЛЬКИ тут ----
class CheckInFacade {
    private final BookingService booking = new BookingService();
    private final GuestRegistry registry = new GuestRegistry();
    private final RoomAssignment assignment = new RoomAssignment();
    private final KeyCardService keyCards;      // передаємо ззовні, щоб він був доступний окремо
    private final BillingAccount billing = new BillingAccount();
    private final HousekeepingService housekeeping = new HousekeepingService();

    CheckInFacade(KeyCardService keyCards) { this.keyCards = keyCards; }

    // Одна проста операція для всіх клієнтів
    void checkIn(String guest, String bookingId) {
        Logger.log("CheckInFacade", "початок заселення гостя " + guest);
        if (!booking.verify(bookingId)) return;
        registry.register(guest);
        int room = assignment.assign(bookingId);
        keyCards.issue(room, guest);
        billing.open(guest, room);              // раніше онлайн-заселення "забувало" цей крок
        housekeeping.notifyRoom(room);
        Logger.log("CheckInFacade", "заселення гостя " + guest + " завершено");
    }
}

// ---- Клієнти фасаду: нічого не знають про підсистеми ----
class ReceptionDesk {
    private final CheckInFacade facade;
    ReceptionDesk(CheckInFacade facade) { this.facade = facade; }

    void checkIn(String guest, String bookingId) {
        Logger.log("ReceptionDesk", "адміністратор заселяє гостя");
        facade.checkIn(guest, bookingId);
    }
}

class OnlineCheckIn {
    private final CheckInFacade facade;
    OnlineCheckIn(CheckInFacade facade) { this.facade = facade; }

    void checkIn(String guest, String bookingId) {
        Logger.log("OnlineCheckIn", "гість заселяється онлайн");
        facade.checkIn(guest, bookingId);       // та сама операція
    }
}
