package variant5_shippingcostcalculator;

import java.math.BigDecimal;

public class ExpressAirStrategy implements ShippingCostStrategy {

    @Override
    public BigDecimal calculateRawCost(Parcel parcel) {

        BigDecimal weight = parcel.getWeightKg();
        BigDecimal distance = parcel.getDistanceKm();

        // $4 за кг або $15 за м³ — обираємо більшу суму
        BigDecimal weightFee = weight.multiply(new BigDecimal("4.00"));

        BigDecimal volumeFee = parcel.getVolumeM3()
                .multiply(new BigDecimal("15.00"));

        BigDecimal highestFee = weightFee.max(volumeFee);

        // $25 базово + більша з двох сум + $0.50 за км
        return new BigDecimal("25.00")
                .add(highestFee)
                .add(distance.multiply(new BigDecimal("0.50")));
    }

    @Override
    public String getName() {
        return "EXPRESS_AIR";
    }
}