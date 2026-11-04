package variant5_vehiclerental.domain;

/**
 * Автомобіль. Information Expert: знає свій тариф та клас, тому сам рахує вартість оренди.
 * Було: LegacyRentalManager читав vehicle.dailyRate і vehicle.classType та рахував сам.
 */
public final class Vehicle {
    private final int id;
    private final String model;
    private final VehicleClassPolicy classPolicy; // Polymorphism замість рядка "Economy"/"Business"/"SUV"
    private final double dailyRate;

    public Vehicle(int id, String model, VehicleClassPolicy classPolicy, double dailyRate) {
        if (classPolicy == null) {
            throw new IllegalArgumentException("Клас авто обов'язковий");
        }
        if (dailyRate <= 0) {
            throw new IllegalArgumentException("Добовий тариф має бути додатним");
        }
        this.id = id;
        this.model = model;
        this.classPolicy = classPolicy;
        this.dailyRate = dailyRate;
    }

    /** Вартість оренди на days діб з урахуванням класу авто. */
    public double rentalCost(int days) {
        return classPolicy.applyMarkup(dailyRate * days);
    }

    public int id() { return id; }
    public String model() { return model; }
}
