import billing.Department;
import keycards.KeyCard;
import keycards.KeyCardPool;
import keycards.Reception;
import stay.Discount;
import stay.SkiPass;
import stay.SpaProcedure;
import stay.StayPackage;

import java.util.List;

import static util.Logger.log;

/** Раннер: окремий метод для кожної ситуації. */
public class Main {

    public static void main(String[] args) {
        runKeyCardsScenario();
        runBillingScenario();
        runPackagesScenario();
    }

    // ---------- Завдання 1: Object Pool ----------
    private static void runKeyCardsScenario() {
        printTitle("Завдання 1. Видача електронних ключ-карт (Object Pool)");

        KeyCardPool pool = new KeyCardPool(5);
        Reception reception = new Reception(pool);

        int[] rooms = {301, 305, 309, 310, 311, 312, 313};
        KeyCard[] cards = new KeyCard[rooms.length];

        // 7 гостей поселяються підряд
        for (int i = 0; i < rooms.length; i++) {
            cards[i] = reception.checkIn("Гість " + (i + 1), rooms[i]);
        }

        // гість з номера 305 виїжджає, його карта K-2 повертається
        reception.checkOut("Гість 2", 305, cards[1]);

        // K-2 видається новому гостю в номер 412
        KeyCard k2 = reception.checkIn("Гість 8", 412);

        // стара карта не відкриває 305
        reception.tryOpenDoor(k2, 305);
        reception.tryOpenDoor(k2, 412);

        pool.printStatistics();
        log("Main", "Механічних ключів видано: " + reception.getMechanicalKeysIssued());
    }

    // ---------- Завдання 2: Singleton ----------
    private static void runBillingScenario() {
        printTitle("Завдання 2. Розрахунки з гостями (Singleton)");

        Department reception = new Department("Рецепція");
        Department restaurant = new Department("Ресторан");
        Department spa = new Department("SPA");
        Department rental = new Department("Прокат");

        reception.charge(305, "Проживання 3 ночі", 4500);
        restaurant.charge(305, "Вечеря", 800);
        spa.charge(305, "Масаж", 1200);
        rental.charge(305, "Прокат лиж", 600);

        reception.issueFinalBill(305);

        boolean same = reception.getBillingId() == restaurant.getBillingId()
                && restaurant.getBillingId() == spa.getBillingId()
                && spa.getBillingId() == rental.getBillingId();
        log("Main", "id служби в усіх підрозділах однаковий: " + same
                + " (id=" + reception.getBillingId() + ")");
    }

    // ---------- Завдання 3: Prototype ----------
    private static void runPackagesScenario() {
        printTitle("Завдання 3. Оформлення пакетів проживання (Prototype)");

        // типовий пакет створюється один раз (дорого)
        StayPackage template = new StayPackage("Лижний тиждень", "півпансіон", "аеропорт — готель",
                new SkiPass(6, "Буковель"));
        template.getSpaProcedures().add(new SpaProcedure("Сауна", 45));
        template.getDiscounts().add(new Discount("Знижка на прокат", 10));

        // пакети гостей — копії типового
        StayPackage guest1 = template.copy();
        guest1.setGuestName("Олена");
        StayPackage guest2 = template.copy();
        guest2.setGuestName("Іван");
        StayPackage guest3 = template.copy();
        guest3.setGuestName("Марія");

        // змінюємо тільки пакет першого гостя (включно з вкладеними даними)
        guest1.getSpaProcedures().add(new SpaProcedure("Масаж", 60));
        guest1.getSkiPass().setDays(8);
        guest1.getDiscounts().get(0).setPercent(15);
        log("Main", "У пакеті гостя Олена додано SPA, продовжено ski-pass, змінено знижку");

        printSideBySide(template.describe("ТИПОВИЙ ПАКЕТ"), guest1.describe("ПАКЕТ ГОСТЯ 1"));
        printSideBySide(template.describe("ТИПОВИЙ ПАКЕТ"), guest2.describe("ПАКЕТ ГОСТЯ 2"));
        printSideBySide(template.describe("ТИПОВИЙ ПАКЕТ"), guest3.describe("ПАКЕТ ГОСТЯ 3"));
    }

    private static void printSideBySide(List<String> left, List<String> right) {
        System.out.println();
        for (int i = 0; i < left.size(); i++) {
            System.out.printf("%-48s | %s%n", left.get(i), right.get(i));
        }
    }

    private static void printTitle(String title) {
        System.out.println();
        System.out.println("==== " + title + " ====");
    }
}
