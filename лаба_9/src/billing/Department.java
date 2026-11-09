package billing;

import static util.Logger.log;

/** Підрозділ готелю (рецепція, ресторан, SPA, прокат). */
public class Department {
    private final String name;
    private final BillingService billing;

    public Department(String name) {
        this.name = name;
        this.billing = BillingService.getInstance(); // усі отримують ту саму службу
        log(name, "Отримано службу розрахунків, id=" + billing.getServiceId());
    }

    public void charge(int room, String description, double amount) {
        billing.addCharge(room, new Charge(name, description, amount));
        log(name, "Нараховано номеру " + room + ": " + description + " — " + amount + " грн");
    }

    /** Підсумковий рахунок при виїзді (формує рецепція). */
    public void issueFinalBill(int room) {
        log(name, "Підсумковий рахунок, номер " + room + ":");
        for (Charge c : billing.getCharges(room)) {
            log(name, "  " + c.getDepartment() + ": " + c.getDescription() + " — " + c.getAmount() + " грн");
        }
        log(name, "  Разом: " + billing.getTotal(room) + " грн (нарахувань: "
                + billing.getCharges(room).size() + ")");
    }

    public int getBillingId() {
        return billing.getServiceId();
    }
}
