import java.time.LocalDateTime;

// Клас-раннер: по одному методу на кожну ситуацію
public class Main {

    public static void main(String[] args) {
        header("ЗАВДАННЯ 1: Decorator - послуги до номера");
        situation1();
        header("ЗАВДАННЯ 2: Facade - заселення гостя");
        situation2();
        header("ЗАВДАННЯ 3: Adapter - система замків");
        situation3();
        header("ЗАВДАННЯ 4: Proxy - доступ до розрахунків");
        situation4();
    }

    static void situation1() {
        Room standard = new StandardRoom(101);
        Logger.log("Main", "вартість стандартного номера: " + standard.getPrice() + " грн/ніч");

        // додаємо послуги "обгортками" під час роботи програми
        Room room = new Parking(new MountainView(new Breakfast(new StandardRoom(102))));
        Logger.log("Main", "опис: " + room.getDescription());
        Logger.log("Main", "підсумкова вартість: " + room.getPrice() + " грн/ніч");

        // інший номер: пізній виїзд + дитяче ліжко
        Room other = new BabyBed(new LateCheckout(new StandardRoom(201)));
        Logger.log("Main", "опис: " + other.getDescription());
        Logger.log("Main", "підсумкова вартість: " + other.getPrice() + " грн/ніч");
    }

    static void situation2() {
        KeyCardService keyCards = new KeyCardService();          // доступний і окремо
        CheckInFacade facade = new CheckInFacade(keyCards);

        new ReceptionDesk(facade).checkIn("Іван Петренко", "BK-1001");
        new OnlineCheckIn(facade).checkIn("Марія Коваль", "BK-1002");

        Logger.log("Main", "гість загубив ключ-карту, перевипуск без заселення");
        keyCards.reissue(305, "Іван Петренко");
    }

    static void situation3() {
        LocalDateTime checkOut = LocalDateTime.of(2026, 11, 18, 12, 0);

        LockDesk desk = new LockDesk(new VendorOneAdapter(new VendorOneLockApi()));
        desk.program(305, checkOut);

        Logger.log("Main", "у новому корпусі встановлено замки іншого виробника");
        LockDesk newDesk = new LockDesk(new VendorTwoAdapter(new VendorTwoLockApi()));
        newDesk.program(305, checkOut);                          // той самий код рецепції
    }

    static void situation4() {
        BillingProxy billing = new BillingProxy(new RealBillingService());
        BillingService service = billing;                         // підрозділи бачать лише інтерфейс

        Employee waiter = new Employee("Олег", Role.WAITER);
        Employee accountant = new Employee("Ірина", Role.ACCOUNTANT);

        service.charge(waiter, 305, "Вечеря", 850);
        service.refund(waiter, 305, 850);                         // має бути відмова
        service.showBill(accountant, 305);
        service.refund(accountant, 305, 850);

        billing.printAudit();
    }

    static void header(String text) {
        System.out.println();
        System.out.println("==== " + text + " ====");
    }
}
