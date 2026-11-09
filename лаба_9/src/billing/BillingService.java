package billing;

import util.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static util.Logger.log;

/** Singleton: єдина служба розрахунків готелю. */
public final class BillingService {
    private static BillingService instance; // єдиний екземпляр

    private final int serviceId;
    private final Map<Integer, List<Charge>> accounts = new HashMap<>(); // номер -> нарахування

    // Приватний конструктор: створити службу через new поза цим класом неможливо
    private BillingService() {
        this.serviceId = 1000 + new Random().nextInt(9000);
        Logger.pause(300); // дорога ініціалізація
        log("Служба розрахунків", "Службу розрахунків ініціалізовано");
    }

    /** Єдина точка доступу. synchronized — щоб екземпляр не створився двічі в різних потоках. */
    public static synchronized BillingService getInstance() {
        if (instance == null) {
            instance = new BillingService();
        }
        return instance;
    }

    public void addCharge(int room, Charge charge) {
        accounts.computeIfAbsent(room, k -> new ArrayList<>()).add(charge);
    }

    public List<Charge> getCharges(int room) {
        List<Charge> charges = accounts.getOrDefault(room, new ArrayList<>());
        return Collections.unmodifiableList(charges);
    }

    public double getTotal(int room) {
        double sum = 0;
        for (Charge c : getCharges(room)) {
            sum += c.getAmount();
        }
        return sum;
    }

    public int getServiceId() {
        return serviceId;
    }
}
