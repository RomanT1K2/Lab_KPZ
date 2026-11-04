package variant5_vehiclerental.infrastructure;

import variant5_vehiclerental.application.RentalCatalog;
import variant5_vehiclerental.application.VehicleClassRegistry;
import variant5_vehiclerental.domain.Client;
import variant5_vehiclerental.domain.Vehicle;

/**
 * Імітація даних, які в Legacy були «зашиті» в метод (client.id = 15, Toyota RAV4, 1800 грн).
 * У реальній системі тут був би запит до БД.
 */
public final class InMemoryRentalCatalog implements RentalCatalog {
    private final VehicleClassRegistry classes;

    public InMemoryRentalCatalog(VehicleClassRegistry classes) {
        this.classes = classes;
    }

    @Override
    public Client findClient(String name) {
        return new Client(15, name, 4, "+380673332211");
    }

    @Override
    public Vehicle findVehicle(String classType) {
        return new Vehicle(505, "Toyota RAV4", classes.find(classType), 1800.0);
    }
}
