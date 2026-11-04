package variant5_vehiclerental.application;

import variant5_vehiclerental.domain.Client;
import variant5_vehiclerental.domain.Vehicle;

/** Indirection: джерело клієнтів та автопарку (БД, API, in-memory). */
public interface RentalCatalog {
    Client findClient(String name);

    Vehicle findVehicle(String classType);
}
