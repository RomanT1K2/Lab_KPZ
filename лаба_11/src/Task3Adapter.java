import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.ZoneOffset;

// ===== ЗАВДАННЯ 3: Підключення системи замків -> шаблон ADAPTER =====
// Чому Adapter: є готовий несумісний інтерфейс замків (змінити не можна),
// а рецепція працює зі своїм. Адаптер перекладає один інтерфейс в інший.

// ---- Інтерфейс, який знає рецепція ----
interface DoorLock {
    // повертає підтвердження у форматі рецепції
    String programAccess(int roomNumber, LocalDateTime checkOut);
}

// ---- Замки виробника 1 (чужий код, змінювати не можна) ----
// кімната = код поверху + код крила, час = секунди від 2000 року
class VendorOneLockApi {
    int setAccess(int floorCode, char wingCode, long secondsSince2000) {
        Logger.log("VendorOneLockApi (замок)", "setAccess(floor=" + floorCode + ", wing=" + wingCode
                + ", seconds=" + secondsSince2000 + ")");
        return 0; // 0 = успіх
    }
}

// ---- Замки виробника 2 для нового корпусу (інший формат) ----
// кімната = рядок "поверх/кімната", час = unix-секунди
class VendorTwoLockApi {
    boolean grantAccess(String roomCode, long unixSeconds) {
        Logger.log("VendorTwoLockApi (замок)", "grantAccess(room=\"" + roomCode
                + "\", unix=" + unixSeconds + ")");
        return true;
    }
}

// ---- Адаптер 1: уся конвертація тільки тут ----
class VendorOneAdapter implements DoorLock {
    private final VendorOneLockApi api;   // той, кого адаптуємо
    VendorOneAdapter(VendorOneLockApi api) { this.api = api; }

    public String programAccess(int roomNumber, LocalDateTime checkOut) {
        Logger.log("VendorOneAdapter (адаптер)", "перетворює номер " + roomNumber + " і дату " + checkOut);
        int floor = roomNumber / 100;                              // 305 -> поверх 3
        char wing = (roomNumber % 100 <= 20) ? 'A' : 'B';          // умовно: кімнати 01-20 = крило A
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.of(2000, 1, 1, 0, 0), checkOut);
        int status = api.setAccess(floor, wing, seconds);          // реальну роботу робить замок
        return status == 0
                ? "Доступ до номера " + roomNumber + " запрограмовано до " + checkOut
                : "Помилка програмування номера " + roomNumber;
    }
}

// ---- Адаптер 2: для нового корпусу ----
class VendorTwoAdapter implements DoorLock {
    private final VendorTwoLockApi api;
    VendorTwoAdapter(VendorTwoLockApi api) { this.api = api; }

    public String programAccess(int roomNumber, LocalDateTime checkOut) {
        Logger.log("VendorTwoAdapter (адаптер)", "перетворює номер " + roomNumber + " і дату " + checkOut);
        String code = (roomNumber / 100) + "/" + String.format("%02d", roomNumber % 100); // "3/05"
        long unix = checkOut.toEpochSecond(ZoneOffset.UTC);
        boolean ok = api.grantAccess(code, unix);
        return ok
                ? "Доступ до номера " + roomNumber + " запрограмовано до " + checkOut
                : "Помилка програмування номера " + roomNumber;
    }
}

// ---- Рецепція: знає лише DoorLock, нічого про виробників ----
class LockDesk {
    private final DoorLock lock;
    LockDesk(DoorLock lock) { this.lock = lock; }

    void program(int room, LocalDateTime checkOut) {
        Logger.log("LockDesk (рецепція)", "програмує доступ до номера " + room);
        String answer = lock.programAccess(room, checkOut);
        Logger.log("LockDesk (рецепція)", "отримано підтвердження: " + answer);
    }
}
