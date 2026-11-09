package keycards;

import util.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static util.Logger.log;

/** Object Pool: фіксований набір карт, які видаються й повертаються. */
public class KeyCardPool {
    private static final String SOURCE = "Пул карт";

    private final List<KeyCard> allCards = new ArrayList<>();
    private final Queue<KeyCard> freeCards = new LinkedList<>();

    /** Усі карти виготовляються один раз, при створенні пулу. */
    public KeyCardPool(int size) {
        for (int i = 1; i <= size; i++) {
            log(SOURCE, "Виготовлення карти K-" + i + " (бланк, кодування, реєстрація в замках)...");
            Logger.pause(200); // дорога операція
            KeyCard card = new KeyCard("K-" + i);
            allCards.add(card);
            freeCards.add(card);
        }
        log(SOURCE, "Пул створено, карт у рецепції: " + size);
    }

    /** Видати карту. Якщо вільних немає — повертає null. */
    public KeyCard acquire(int room, String validUntil) {
        if (freeCards.isEmpty()) {
            return null;
        }
        KeyCard card = freeCards.poll();
        card.program(room, validUntil); // карта завжди кодується перед видачею
        log(SOURCE, "Карта " + card.getId() + " → номер " + room);
        return card;
    }

    /** Повернути карту: стерти доступ і покласти назад у пул. */
    public void release(KeyCard card) {
        if (!allCards.contains(card) || freeCards.contains(card)) {
            log(SOURCE, "Помилка: карту " + card.getId() + " не можна повернути");
            return;
        }
        int oldRoom = card.erase();
        freeCards.add(card);
        log(SOURCE, card.getId() + " повернено, доступ до " + oldRoom + " стерто");
    }

    public void printStatistics() {
        log(SOURCE, "Статистика видач:");
        for (KeyCard card : allCards) {
            log(SOURCE, "  " + card.getId() + ": видач " + card.getIssueCount());
        }
        log(SOURCE, "Загальна кількість карт: " + allCards.size());
    }
}
