package billing;

/** Одне нарахування за послугу. */
public class Charge {
    private final String department;
    private final String description;
    private final double amount;

    public Charge(String department, String description, double amount) {
        this.department = department;
        this.description = description;
        this.amount = amount;
    }

    public String getDepartment() {
        return department;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
