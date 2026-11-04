package variant5_vehiclerental.domain;

/** Polymorphism: клас Economy — без надбавки. */
public final class EconomyPolicy implements VehicleClassPolicy {
    @Override
    public double applyMarkup(double baseCost) {
        return baseCost;
    }
}
