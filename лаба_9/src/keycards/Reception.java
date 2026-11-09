package keycards;

import static util.Logger.log;

/**
 * Рецепція (клієнт пулу).
 * Стратегія, коли карт немає: гість отримує ТИМЧАСОВИЙ МЕХАНІЧНИЙ КЛЮЧ.
 */
public class Reception {
    private static final String SOURCE = "Рецепція";

    private final KeyCardPool pool;
    private int mechanicalKeysIssued = 0;

    public Reception(KeyCardPool pool) {
        this.pool = pool;
    }

    /** Поселення гостя. Повертає карту або null, якщо видано механічний ключ. */
    public KeyCard checkIn(String guest, int room) {
        KeyCard card = pool.acquire(room, "до виїзду");
        if (card == null) {
            mechanicalKeysIssued++;
            log(SOURCE, "Вільних карт немає: " + guest + " (номер " + room
                    + ") отримує тимчасовий механічний ключ");
        } else {
            log(SOURCE, guest + " поселено в номер " + room + ", карта " + card.getId());
        }
        return card;
    }

    /** Виїзд гостя. */
    public void checkOut(String guest, int room, KeyCard card) {
        log(SOURCE, guest + " виїжджає з номера " + room);
        if (card != null) {
            pool.release(card);
        } else {
            log(SOURCE, "Механічний ключ від номера " + room + " повернено");
        }
    }

    /** Спроба відкрити двері карткою. */
    public void tryOpenDoor(KeyCard card, int room) {
        if (card.opens(room)) {
            log("Замок " + room, "Карта " + card.getId() + ": доступ дозволено");
        } else {
            log("Замок " + room, "Карта " + card.getId() + ": ВІДМОВА");
        }
    }

    public int getMechanicalKeysIssued() {
        return mechanicalKeysIssued;
    }
}
