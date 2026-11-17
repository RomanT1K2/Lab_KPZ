package hotel.command;

import hotel.common.Journal;

/**
 * Базова команда нарахування послуги.
 * Зберігає, хто (підрозділ), що і на яку суму нарахував.
 */
public abstract class ChargeCommand implements Command {
    private final GuestAccount account; // Receiver
    private final String department;
    private final String description;
    private final double amount;

    private boolean executed = false;
    private boolean reversed = false;

    public ChargeCommand(GuestAccount account, String department, String description, double amount) {
        this.account = account;
        this.department = department;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public void execute() {
        if (executed) {
            Journal.log(department, "нарахування вже виконано: " + description);
            return;
        }
        account.addOperation(new Operation(Journal.now(), department, description, amount));
        executed = true;
        Journal.log(department, "НАРАХУВАННЯ: " + description + " " + amount + " грн");
    }

    @Override
    public void undo() {
        if (!executed || reversed) {
            Journal.log(department, "сторнування неможливе: " + description);
            return;
        }
        // Не видаляємо запис, а додаємо зворотну операцію зі знаком "-"
        account.addOperation(new Operation(Journal.now(), department, "СТОРНО: " + description, -amount));
        reversed = true;
        Journal.log(department, "СТОРНУВАННЯ: " + description + " " + (-amount) + " грн");
    }
}
