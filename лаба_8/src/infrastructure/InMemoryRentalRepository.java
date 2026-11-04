package variant5_vehiclerental.infrastructure;

import java.util.ArrayList;
import java.util.List;
import variant5_vehiclerental.application.RentalRepository;
import variant5_vehiclerental.domain.RentalAgreement;

/** Імітація БД (було: println("[База даних] ...") прямо в бізнес-методі). */
public final class InMemoryRentalRepository implements RentalRepository {
    private final List<RentalAgreement> storage = new ArrayList<>();
    private int nextId = 9012; // у Legacy id договору був «зашитий» 9012

    @Override
    public RentalAgreement save(RentalAgreement agreement) {
        RentalAgreement saved = agreement.withId(nextId++);
        storage.add(saved);
        System.out.println("[База даних] Ваучер #" + saved.id() + " зафіксовано в системі.");
        return saved;
    }
}
