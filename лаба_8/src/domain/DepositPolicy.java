package variant5_vehiclerental.domain;

/**
 * Правило застави. Базова застава 5000 грн, +3000 грн для водіїв зі стажем менше 3 років.
 * Відповідь на питання «чи малий стаж» дає сам Client (Закон Деметри: не ліземо в його поля).
 */
public final class DepositPolicy {
    private static final double BASE_DEPOSIT = 5000.0;
    private static final double LOW_EXPERIENCE_SURCHARGE = 3000.0;

    public static DepositPolicy standard() {
        return new DepositPolicy();
    }

    public double depositFor(Client client) {
        return client.hasLowExperience() ? BASE_DEPOSIT + LOW_EXPERIENCE_SURCHARGE : BASE_DEPOSIT;
    }
}
