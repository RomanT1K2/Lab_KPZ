package variant5_vehiclerental.application;

import variant5_vehiclerental.domain.RentalAgreement;

/** DTO-відповідь: плоскі дані для виводу. CLI не знає про доменні класи (Indirection). */
public record RentCarResponse(int rentalId, String clientName, String clientPhone, String vehicleModel,
                              double rentalTotal, double insuranceTotal, double depositAmount,
                              double finalPrice) {

    public static RentCarResponse from(RentalAgreement a) {
        return new RentCarResponse(a.id(), a.clientName(), a.clientPhone(), a.vehicleModel(),
                a.rentalTotal(), a.insuranceTotal(), a.depositAmount(), a.finalPrice());
    }
}
