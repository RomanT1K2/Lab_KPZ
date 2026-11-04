package variant5_vehiclerental.application;

import variant5_vehiclerental.domain.RentalAgreement;

/**
 * Збирає договір із запиту: знаходить клієнта й авто, обирає страховку.
 * Сам нічого не рахує — розрахунок виконує RentalAgreement.create (Information Expert).
 */
public final class RentalAgreementFactory {
    private final RentalCatalog catalog;
    private final InsurancePlanRegistry insurancePlans;

    public RentalAgreementFactory(RentalCatalog catalog, InsurancePlanRegistry insurancePlans) {
        this.catalog = catalog;
        this.insurancePlans = insurancePlans;
    }

    public RentalAgreement create(RentCarRequest request) {
        return RentalAgreement.create(
                catalog.findClient(request.clientName()),
                catalog.findVehicle(request.carClass()),
                request.days(),
                insurancePlans.find(request.insuranceType()));
    }
}
