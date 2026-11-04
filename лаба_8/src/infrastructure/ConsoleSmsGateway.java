package variant5_vehiclerental.infrastructure;

import variant5_vehiclerental.application.NotificationGateway;
import variant5_vehiclerental.domain.RentalAgreement;

/** Імітація SMS-шлюзу (було: println("[SMS] ...") прямо в бізнес-методі). */
public final class ConsoleSmsGateway implements NotificationGateway {
    @Override
    public void sendRentalConfirmation(RentalAgreement agreement) {
        System.out.println("[SMS] Підтвердження оренди надіслано на номер " + agreement.clientPhone());
    }
}
