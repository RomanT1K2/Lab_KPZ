package hotel.command;

import java.util.ArrayList;
import java.util.List;

/** Рахунок гостя (Receiver). Зберігає повну історію операцій. */
public class GuestAccount {
    private final String guestName;
    private final List<Operation> history = new ArrayList<>();

    public GuestAccount(String guestName) {
        this.guestName = guestName;
    }

    public void addOperation(Operation op) {
        history.add(op); // тільки додаємо, ніколи не видаляємо
    }

    public double getBalance() {
        double sum = 0;
        for (Operation op : history) {
            sum += op.getAmount();
        }
        return sum;
    }

    // Поточний рахунок: операції + підсумок
    public void printBill() {
        System.out.println("--- Рахунок гостя " + guestName + " ---");
        for (Operation op : history) {
            System.out.println("  " + op);
        }
        System.out.printf("  РАЗОМ до сплати: %.2f грн%n", getBalance());
    }

    // Повна історія операцій
    public void printHistory() {
        System.out.println("=== Повна історія операцій: " + guestName + " (записів: " + history.size() + ") ===");
        int i = 1;
        for (Operation op : history) {
            System.out.println("  " + i++ + ". " + op);
        }
    }
}
