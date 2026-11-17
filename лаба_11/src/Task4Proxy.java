import java.util.*;

// ===== ЗАВДАННЯ 4: Доступ до служби розрахунків -> шаблон PROXY =====
// Чому Proxy: потрібно контролювати доступ (за роллю) і вести аудит,
// а підрозділи мають працювати з тим самим інтерфейсом, не знаючи про перевірки.

enum Role { WAITER, SPA, RENTAL, RECEPTION, ACCOUNTANT }

class Employee {
    final String name;
    final Role role;
    Employee(String name, Role role) { this.name = name; this.role = role; }
}

// Спільний інтерфейс для реальної служби і для проксі
interface BillingService {
    void charge(Employee e, int room, String what, int amount);  // нарахувати
    void showBill(Employee e, int room);                         // повний рахунок
    void refund(Employee e, int room, int amount);               // повернення коштів
}

// Реальна служба: просто виконує операції, нічого не перевіряє
class RealBillingService implements BillingService {
    private final Map<Integer, List<String>> bills = new HashMap<>();

    public void charge(Employee e, int room, String what, int amount) {
        bills.computeIfAbsent(room, r -> new ArrayList<>()).add(what + ": +" + amount + " грн");
        Logger.log("RealBillingService (реальна служба)", "нараховано '" + what + "' " + amount + " грн, номер " + room);
    }

    public void showBill(Employee e, int room) {
        Logger.log("RealBillingService (реальна служба)", "повний рахунок номера " + room + ": "
                + bills.getOrDefault(room, new ArrayList<>()));
    }

    public void refund(Employee e, int room, int amount) {
        bills.computeIfAbsent(room, r -> new ArrayList<>()).add("повернення: -" + amount + " грн");
        Logger.log("RealBillingService (реальна служба)", "повернено " + amount + " грн, номер " + room);
    }
}

// Проксі: перевіряє роль, веде аудит, і лише потім передає виклик реальній службі
class BillingProxy implements BillingService {
    private final BillingService real;
    private final List<String> auditLog = new ArrayList<>();

    BillingProxy(BillingService real) { this.real = real; }

    public void charge(Employee e, int room, String what, int amount) {
        // нараховувати можуть усі ролі, тому перевірка ролі не потрібна
        audit(e, "нарахування '" + what + "' " + amount + " грн, номер " + room);
        real.charge(e, room, what, amount);
    }

    public void showBill(Employee e, int room) {
        if (!isFinanceStaff(e)) { deny(e, "перегляд рахунку", "Недостатньо прав для перегляду рахунку"); return; }
        audit(e, "перегляд рахунку номера " + room);
        real.showBill(e, room);
    }

    public void refund(Employee e, int room, int amount) {
        if (!isFinanceStaff(e)) { deny(e, "повернення коштів", "Недостатньо прав для операції повернення"); return; }
        audit(e, "повернення " + amount + " грн, номер " + room);
        real.refund(e, room, amount);
    }

    private boolean isFinanceStaff(Employee e) {
        return e.role == Role.RECEPTION || e.role == Role.ACCOUNTANT;
    }

    private void deny(Employee e, String operation, String message) {
        auditLog.add(e.name + " (" + e.role + "): СПРОБА " + operation + " - ВІДМОВЛЕНО");
        Logger.log("BillingProxy (проксі)", "ВІДМОВА для " + e.name + ": " + message);
    }

    private void audit(Employee e, String action) {
        auditLog.add(e.name + " (" + e.role + "): " + action);
        Logger.log("BillingProxy (проксі)", "аудит: " + e.name + " - " + action);
    }

    void printAudit() {
        Logger.log("BillingProxy (проксі)", "=== ЖУРНАЛ АУДИТУ ===");
        for (String line : auditLog) Logger.log("AuditLog", line);
    }
}
