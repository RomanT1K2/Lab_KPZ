package hotel;

import hotel.chain.*;
import hotel.command.*;
import hotel.common.Journal;

import java.io.PrintStream;

/** Раннер: окремий метод для кожної ситуації. */
public class Main {

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8")); // щоб кирилиця виводилась правильно
        situation1GuestRequests();
        System.out.println();
        situation2GuestBill();
    }

    // ===== Завдання 1: Chain of Responsibility =====
    static void situation1GuestRequests() {
        System.out.println("##### СИТУАЦІЯ 1: обробка запитів гостя #####");
        Journal.setTime(10, 0);
        RequestDesk desk = new RequestDesk();

        System.out.println("\n--- Денна зміна: 4 запити різних типів ---");
        desk.setChain(ShiftChains.dayShift());
        desk.submit(new GuestRequest(101, RequestType.TOWELS, "потрібні додаткові рушники"));
        desk.submit(new GuestRequest(205, RequestType.CLEANING, "прибрати номер"));
        desk.submit(new GuestRequest(312, RequestType.MALFUNCTION, "не працює опалення"));
        desk.submit(new GuestRequest(118, RequestType.COMPLAINT, "шум у коридорі"));

        System.out.println("\n--- Перебудова ланцюга на нічну зміну ---");
        Journal.setTime(23, 30);
        Journal.log("Система", "ланцюг перебудовано: Рецепція -> Техслужба -> Менеджер (покоївок немає)");
        desk.setChain(ShiftChains.nightShift());
        desk.submit(new GuestRequest(205, RequestType.CLEANING, "прибрати номер"));

        System.out.println("\n--- Запит, який не може обробити жоден рівень ---");
        desk.submit(new GuestRequest(407, RequestType.OTHER, "викликати вертоліт на вершину"));
    }

    // ===== Завдання 2: Command =====
    static void situation2GuestBill() {
        System.out.println("##### СИТУАЦІЯ 2: нарахування послуг на рахунок #####");
        Journal.setTime(19, 0);
        GuestAccount account = new GuestAccount("Іваненко О. (номер 205)");
        BillingService billing = new BillingService();

        System.out.println("\n--- Підрозділи нараховують послуги ---");
        Command dinner = new DinnerCharge(account, 1200);
        Command massage = new MassageCharge(account, 800); // це нарахування буде помилковим
        Command skis = new SkiRentalCharge(account, 500);
        billing.execute(dinner);
        billing.execute(massage);
        billing.execute(skis);

        System.out.println();
        account.printBill();

        System.out.println("\n--- Сторнування помилкового нарахування SPA ---");
        billing.undo(massage);

        System.out.println();
        account.printBill();

        System.out.println();
        account.printHistory();
    }
}
