package variant5_vehiclerental.application;

import variant5_vehiclerental.domain.RentalAgreement;

/** Protected Variations: SMS-провайдер можна замінити (email, viber) без змін у ядрі. */
public interface NotificationGateway {
    void sendRentalConfirmation(RentalAgreement agreement);
}
