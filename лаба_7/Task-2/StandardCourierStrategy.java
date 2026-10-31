package variant5_shippingcostcalculator;

import java.math.BigDecimal;

public class StandardCourierStrategy implements ShippingCostStrategy {

    @Override
    public BigDecimal calculateRawCost(Parcel parcel) {

        BigDecimal weight = parcel.getWeightKg();
        BigDecimal distance = parcel.getDistanceKm();

        // $5 базово + $1.50 за кожен кг + $0.10 за кожен км
        return new BigDecimal("5.00")
                .add(weight.multiply(new BigDecimal("1.50")))
                .add(distance.multiply(new BigDecimal("0.10")));
    }

    @Override
    public String getName() {
        return "STANDARD_COURIER";
    }
}