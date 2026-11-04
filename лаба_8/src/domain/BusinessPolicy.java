package variant5_vehiclerental.domain;

/** Polymorphism: клас Business — надбавка 30% (було: rental *= 1.3). */
public final class BusinessPolicy implements VehicleClassPolicy {
    private static final double MARKUP = 1.3;

    @Override
    public double applyMarkup(double baseCost) {
        return baseCost * MARKUP;
    }
}
