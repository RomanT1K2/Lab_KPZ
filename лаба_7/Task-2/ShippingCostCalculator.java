package variant5_shippingcostcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ShippingCostCalculator {

    public BigDecimal calculateCost(Parcel parcel, ShippingCostStrategy strategy) {

        Objects.requireNonNull(parcel, "Parcel не може бути null");
        Objects.requireNonNull(strategy, "Strategy не може бути null");

        BigDecimal basePrice = strategy.calculateRawCost(parcel);

        basePrice = basePrice.setScale(2, RoundingMode.HALF_UP);

        System.out.println(
                "[SHIPPING] Посилка " + parcel.getTrackingNumber()
                + " (" + strategy.getName() + "): "
                + "Вага=" + parcel.getWeightKg() + "кг, "
                + "Відстань=" + parcel.getDistanceKm() + "км "
                + "-> Вартість: $" + basePrice
        );

        return basePrice;
    }
}