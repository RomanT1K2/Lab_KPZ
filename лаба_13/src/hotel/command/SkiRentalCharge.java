package hotel.command;

/** Нарахування: прокат лиж. */
public class SkiRentalCharge extends ChargeCommand {
    public SkiRentalCharge(GuestAccount account, double amount) {
        super(account, "Прокат", "Прокат лиж", amount);
    }
}
