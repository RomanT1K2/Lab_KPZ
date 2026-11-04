package variant5_vehiclerental.application;

import variant5_vehiclerental.domain.RentalAgreement;

/** Indirection + Protected Variations: контролер не знає, куди саме зберігаються договори. */
public interface RentalRepository {
    /** Зберігає договір і повертає його копію з присвоєним id. */
    RentalAgreement save(RentalAgreement agreement);
}
