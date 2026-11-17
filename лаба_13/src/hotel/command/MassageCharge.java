package hotel.command;

/** Нарахування: масаж у SPA. */
public class MassageCharge extends ChargeCommand {
    public MassageCharge(GuestAccount account, double amount) {
        super(account, "SPA", "Масаж", amount);
    }
}
