package variant5_shippingcostcalculator;

import java.math.BigDecimal;

public class LockerPickupStrategy implements ShippingCostStrategy {

    @Override
    public BigDecimal calculateRawCost(Parcel parcel) {

        BigDecimal weight = parcel.getWeightKg();

        // До 5 кг — фіксована вартість $3.00
        if (weight.compareTo(new BigDecimal("5.00")) <= 0) {
            return new BigDecimal("3.00");
        }

        // Понад 5 кг — $0.80 за кожен додатковий кг
        BigDecimal extraWeight = weight.subtract(new BigDecimal("5.00"));

        return new BigDecimal("3.00")
                .add(extraWeight.multiply(new BigDecimal("0.80")));
    }

    @Override
    public String getName() {
        return "LOCKER_PICKUP";
    }
}