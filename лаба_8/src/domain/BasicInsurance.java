package variant5_vehiclerental.domain;

/** Polymorphism: Basic — 200 грн/добу (було: 200.0 * days). */
public final class BasicInsurance implements InsurancePlan {
    private static final double DAILY_RATE = 200.0;

    @Override
    public double cost(int days) {
        return DAILY_RATE * days;
    }
}
