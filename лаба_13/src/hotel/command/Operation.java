package hotel.command;

/** Один запис в історії рахунку. Після створення не змінюється і не видаляється. */
public class Operation {
    private final String time;
    private final String department;
    private final String description;
    private final double amount; // "+" нарахування, "-" сторно

    public Operation(String time, String department, String description, double amount) {
        this.time = time;
        this.department = department;
        this.description = description;
        this.amount = amount;
    }

    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return String.format("[%s] %-10s %-22s %+9.2f грн", time, department, description, amount);
    }
}
