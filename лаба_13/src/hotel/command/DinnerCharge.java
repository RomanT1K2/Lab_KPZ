package hotel.command;

/** Нарахування: вечеря в ресторані. */
public class DinnerCharge extends ChargeCommand {
    public DinnerCharge(GuestAccount account, double amount) {
        super(account, "Ресторан", "Вечеря", amount);
    }
}
