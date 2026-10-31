package variant5_shippingcostcalculator;

import java.math.BigDecimal;

public interface ShippingCostStrategy {

    BigDecimal calculateRawCost(Parcel parcel);

    String getName();
}