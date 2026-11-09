package stay;

import util.Logger;

import java.util.ArrayList;
import java.util.List;

import static util.Logger.log;

/** Пакет проживання (Concrete Prototype). */
public class StayPackage implements PackagePrototype {
    private final String name;
    private String guestName = "—";
    private final String meals;
    private final String transfer;
    private final List<SpaProcedure> spaProcedures = new ArrayList<>();
    private final SkiPass skiPass;
    private final List<Discount> discounts = new ArrayList<>();

    /** Звичайний конструктор: дороге налаштування пакета з нуля. */
    public StayPackage(String name, String meals, String transfer, SkiPass skiPass) {
        log("Пакет «" + name + "»", "Дороге налаштування пакета (харчування, трансфер, SPA, ski-pass, знижки)...");
        Logger.pause(500);
        this.name = name;
        this.meals = meals;
        this.transfer = transfer;
        this.skiPass = skiPass;
    }

    /** Копіювальний конструктор: глибока копія, без дорогого налаштування. */
    private StayPackage(StayPackage other) {
        this.name = other.name;
        this.guestName = other.guestName;
        this.meals = other.meals;
        this.transfer = other.transfer;
        this.skiPass = new SkiPass(other.skiPass);
        for (SpaProcedure p : other.spaProcedures) {
            this.spaProcedures.add(new SpaProcedure(p));
        }
        for (Discount d : other.discounts) {
            this.discounts.add(new Discount(d));
        }
    }

    @Override
    public StayPackage copy() {
        log("Пакет «" + name + "»", "Створено копію без повторного налаштування");
        return new StayPackage(this);
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public List<SpaProcedure> getSpaProcedures() {
        return spaProcedures;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public SkiPass getSkiPass() {
        return skiPass;
    }

    /** Опис пакета рядками (для виведення поруч). */
    public List<String> describe(String title) {
        List<String> lines = new ArrayList<>();
        lines.add(title);
        lines.add("Гість: " + guestName);
        lines.add("Харчування: " + meals);
        lines.add("Трансфер: " + transfer);
        lines.add("SPA: " + (spaProcedures.isEmpty() ? "—" : spaProcedures.toString()));
        lines.add("Ski-pass: " + skiPass);
        lines.add("Знижки: " + (discounts.isEmpty() ? "—" : discounts.toString()));
        return lines;
    }
}
