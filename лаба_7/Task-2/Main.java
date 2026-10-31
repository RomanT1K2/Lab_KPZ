package variant5_shippingcostcalculator;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Parcel parcel = new Parcel(
                "UA123456",
                new BigDecimal("10.00"),
                new BigDecimal("0.50"),
                new BigDecimal("100.00")
        );

        ShippingCostCalculator calculator = new ShippingCostCalculator();

        calculator.calculateCost(parcel, new StandardCourierStrategy());

        calculator.calculateCost(parcel, new ExpressAirStrategy());

        calculator.calculateCost(parcel, new OvernightExpressStrategy());

        calculator.calculateCost(parcel, new LockerPickupStrategy());

        // Нова стратегія
        calculator.calculateCost(parcel, new SameDayDeliveryStrategy());
    }
}