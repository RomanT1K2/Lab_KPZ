package variant5_shippingcostcalculator;

import java.math.BigDecimal;

public class OvernightExpressStrategy implements ShippingCostStrategy {

    @Override
    public BigDecimal calculateRawCost(Parcel parcel) {

        BigDecimal weight = parcel.getWeightKg();

        // $50 базово + $3.00 за кожен кг
        return new BigDecimal("50.00")
                .add(weight.multiply(new BigDecimal("3.00")));
    }

    @Override
    public String getName() {
        return "OVERNIGHT_EXPRESS";
    }
}