package variant5_shippingcostcalculator;

import java.math.BigDecimal;

public class SameDayDeliveryStrategy implements ShippingCostStrategy {

    @Override
    public BigDecimal calculateRawCost(Parcel parcel) {

        BigDecimal weight = parcel.getWeightKg();
        BigDecimal distance = parcel.getDistanceKm();

        // $12 базово + $2 за кожен кг + $0.20 за кожен км
        return new BigDecimal("12.00")
                .add(weight.multiply(new BigDecimal("2.00")))
                .add(distance.multiply(new BigDecimal("0.20")));
    }

    @Override
    public String getName() {
        return "SAME_DAY_DELIVERY";
    }
}