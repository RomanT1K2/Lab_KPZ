package variant5_vehiclerental.domain;

/** Polymorphism: Full — 500 грн/добу (було: 500.0 * days). */
public final class FullInsurance implements InsurancePlan {
    private static final double DAILY_RATE = 500.0;

    @Override
    public double cost(int days) {
        return DAILY_RATE * days;
    }
}
