package variant5_vehiclerental.application;

/**
 * GRASP Controller (Use-Case Controller): перший об'єкт за межами CLI.
 * Не містить ні System.out / Scanner, ні бізнес-обчислень — лише координує:
 *   запит -> створити договір (factory) -> зареєструвати (registrar) -> відповідь.
 */
public final class RentalController {
    private final RentalAgreementFactory factory;
    private final RentalRegistrar registrar;

    public RentalController(RentalAgreementFactory factory, RentalRegistrar registrar) {
        this.factory = factory;
        this.registrar = registrar;
    }

    public RentCarResponse rent(RentCarRequest request) {
        return registrar.register(factory.create(request));
    }
}
