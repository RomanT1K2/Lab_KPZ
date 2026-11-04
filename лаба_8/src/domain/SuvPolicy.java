package variant5_vehiclerental.domain;

/** Polymorphism: клас SUV — надбавка 20% (було: rental *= 1.2). */
public final class SuvPolicy implements VehicleClassPolicy {
    private static final double MARKUP = 1.2;

    @Override
    public double applyMarkup(double baseCost) {
        return baseCost * MARKUP;
    }
}
