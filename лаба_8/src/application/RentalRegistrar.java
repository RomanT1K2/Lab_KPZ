package variant5_vehiclerental.application;

import variant5_vehiclerental.domain.RentalAgreement;

/**
 * Обробник команди (Indirection) — «післяобробка» договору: зберегти, сповістити, сформувати відповідь.
 * Виділений із контролера, щоб контролер лишався тонким і мав CBO ≤ 4.
 */
public final class RentalRegistrar {
    private final RentalRepository repository;
    private final NotificationGateway notifier;

    public RentalRegistrar(RentalRepository repository, NotificationGateway notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    public RentCarResponse register(RentalAgreement agreement) {
        RentalAgreement saved = repository.save(agreement);
        notifier.sendRentalConfirmation(saved);
        return RentCarResponse.from(saved);
    }
}
