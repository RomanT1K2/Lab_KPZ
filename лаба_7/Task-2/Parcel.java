package variant5_shippingcostcalculator;

import java.math.BigDecimal;

public class Parcel {

    private final String trackingNumber;
    private final BigDecimal weightKg;
    private final BigDecimal volumeM3;
    private final BigDecimal distanceKm;

    public Parcel(
            String trackingNumber,
            BigDecimal weightKg,
            BigDecimal volumeM3,
            BigDecimal distanceKm) {

        this.trackingNumber = trackingNumber;
        this.weightKg = weightKg;
        this.volumeM3 = volumeM3;
        this.distanceKm = distanceKm;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public BigDecimal getWeightKg() {
        return weightKg;
    }

    public BigDecimal getVolumeM3() {
        return volumeM3;
    }

    public BigDecimal getDistanceKm() {
        return distanceKm;
    }
}