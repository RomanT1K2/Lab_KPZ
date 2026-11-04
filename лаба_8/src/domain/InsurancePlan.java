package variant5_vehiclerental.domain;

/** Protected Variations: стабільний контракт страхового плану. */
public interface InsurancePlan {
    /** Вартість страховки за вказану кількість діб. */
    double cost(int days);
}
