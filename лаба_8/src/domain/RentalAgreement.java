package variant5_vehiclerental.domain;

/**
 * Агрегат «Договір оренди». Information Expert + Creator:
 *  - володіє даними (клієнт, авто, доби, страховка), тому сам рахує підсумки;
 *  - створюється ТІЛЬКИ через статичну фабрику create(...) і завжди у валідному стані;
 *  - імутабельний: змінити суму чи статус ззовні неможливо.
 */
public final class RentalAgreement {
    private static final String APPROVED = "Approved";
    private static final int NOT_SAVED_ID = 0;

    private final int id;
    private final Client client;
    private final Vehicle vehicle;
    private final int days;
    private final InsurancePlan insurance;
    private final double rentalTotal;
    private final double insuranceTotal;
    private final double depositAmount;
    private final String status;

    private RentalAgreement(int id, Client client, Vehicle vehicle, int days, InsurancePlan insurance,
                            double rentalTotal, double insuranceTotal, double depositAmount) {
        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.days = days;
        this.insurance = insurance;
        this.rentalTotal = rentalTotal;
        this.insuranceTotal = insuranceTotal;
        this.depositAmount = depositAmount;
        this.status = APPROVED;
    }

    /** Creator (Static Factory Method): перевіряє вхідні дані й одразу обчислює всі суми. */
    public static RentalAgreement create(Client client, Vehicle vehicle, int days, InsurancePlan insurance) {
        if (client == null || vehicle == null || insurance == null) {
            throw new IllegalArgumentException("Клієнт, авто та страховка обов'язкові");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Кількість діб має бути більшою за 0");
        }
        return new RentalAgreement(
                NOT_SAVED_ID, client, vehicle, days, insurance,
                vehicle.rentalCost(days),                       // Information Expert: Vehicle
                insurance.cost(days),                           // Information Expert: InsurancePlan
                DepositPolicy.standard().depositFor(client));   // правило застави
    }

    /** Повертає копію договору з присвоєним ідентифікатором (договір імутабельний). */
    public RentalAgreement withId(int newId) {
        return new RentalAgreement(newId, client, vehicle, days, insurance,
                rentalTotal, insuranceTotal, depositAmount);
    }

    /** До сплати за оренду + страховку (застава повертається, тому не входить — як у Legacy). */
    public double finalPrice() {
        return rentalTotal + insuranceTotal;
    }

    public int id() { return id; }
    public double rentalTotal() { return rentalTotal; }
    public double insuranceTotal() { return insuranceTotal; }
    public double depositAmount() { return depositAmount; }
    public String status() { return status; }

    // «Tell, don't ask» (Закон Деметри): зовнішній код не пише agreement.client().name()
    public String clientName() { return client.name(); }
    public String clientPhone() { return client.phone(); }
    public String vehicleModel() { return vehicle.model(); }
}
