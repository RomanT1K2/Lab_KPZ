package hotel;

import hotel.bridge.*;
import hotel.composite.*;
import hotel.flyweight.*;
import hotel.util.Logger;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/** Клас-раннер: окремий метод для кожної проблемної ситуації. */
public class Main {

    public static void main(String[] args) {
        // щоб українські літери коректно виводились у консолі (особливо у Windows)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("===== ЗАВДАННЯ 1: Календар зайнятості (Flyweight) =====");
        task1Flyweight();

        System.out.println();
        System.out.println("===== ЗАВДАННЯ 2: Пакети проживання (Composite) =====");
        task2Composite();

        System.out.println();
        System.out.println("===== ЗАВДАННЯ 3: Керування обладнанням (Bridge) =====");
        task3Bridge();
    }

    // ---------------------------------------------------------
    static void task1Flyweight() {
        OccupancyCalendar calendar = new OccupancyCalendar();
        LocalDate start = LocalDate.of(2026, 12, 1);

        Logger.log("Календар", "починаємо формувати календар: 60 номерів x 365 ночей");
        calendar.build(60, 365, start);

        // кількість записів і кількість об'єктів категорій
        Logger.log("Календар", "кількість записів календаря: " + calendar.getEntriesCount());
        Logger.log("Календар", "кількість об'єктів категорій: " + RoomCategoryFactory.getCreatedCount());

        // два записи ОДНОГО номера на різні дати
        CalendarEntry first = calendar.find(105, start);
        CalendarEntry second = calendar.find(105, start.plusDays(100));
        Logger.log("Календар", "запис 1: " + first);
        Logger.log("Календар", "запис 2: " + second);

        // == порівнює посилання: true означає "це один і той самий об'єкт у пам'яті"
        boolean same = first.getCategory() == second.getCategory();
        Logger.log("Календар", "посилаються на той самий об'єкт категорії? " + same);
    }

    // ---------------------------------------------------------
    static void task2Composite() {
        // Вкладений пакет "Дитячий клуб"
        ServicePackage kidsClub = new ServicePackage("Дитячий клуб");
        kidsClub.add(new SingleService("Заняття з аніматором", 1200));
        kidsClub.add(new SingleService("Екскурсія на ферму", 800));

        // Пакет "Сімейний тиждень": послуги + пакет усередині
        ServicePackage family = new ServicePackage("Сімейний тиждень");
        family.add(new SingleService("Проживання 7 ночей", 14000));
        family.add(new SingleService("Харчування 7 днів", 7000));
        family.add(kidsClub);

        // Окремий пакет "SPA-вікенд"
        ServicePackage spa = new ServicePackage("SPA-вікенд");
        spa.add(new SingleService("Масаж", 1500));
        spa.add(new SingleService("Сауна", 900));
        spa.add(new SingleService("Обгортання", 1100));

        // 1) дерево з вартістю кожного вузла
        family.print("");

        // 2) вартість будь-якого вузла - ТИМ САМИМ викликом printCost()
        printCost(family);
        printCost(kidsClub);
        printCost(spa);
        printCost(new SingleService("Окрема послуга: екскурсія", 800));

        // 3) перелік послуг для вкладеного пакета
        Logger.log("Рахунок", "послуги пакета \"Дитячий клуб\": " + kidsClub.getServiceNames());

        // 4) вкладення на ще глибший рівень - без змін у коді розрахунку
        ServicePackage vip = new ServicePackage("VIP-відпочинок");
        vip.add(family);
        vip.add(spa);
        printCost(vip);
    }

    /** Один метод для рахунку: приймає і послугу, і пакет (будь-якої вкладеності). */
    static void printCost(ServiceComponent component) {
        Logger.log("Рахунок", "вартість \"" + component.getName() + "\": " + component.getPrice() + " грн");
    }

    // ---------------------------------------------------------
    static void task3Bridge() {
        Equipment conditioner = new AirConditioner();
        Equipment curtains = new ElectricCurtains();

        // увімкнути кондиціонер з настінної панелі і з застосунку
        ControlDevice panel = new WallPanel(conditioner);
        ControlDevice app = new MobileApp(conditioner);
        panel.turnOn();
        app.turnOn();

        // закрити штори з пульта
        ControlDevice remote = new RemoteController(curtains);
        remote.turnOff();

        // додали теплу підлогу (1 новий клас FloorHeating) і керуємо з застосунку
        Equipment floor = new FloorHeating();
        app.setEquipment(floor); // зміна обладнання під час виконання програми
        app.turnOn();
    }
}
