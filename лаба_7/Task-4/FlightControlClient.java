package variant5_vehiclecontrolsystem;

public class FlightControlClient {

    private final Flyable vehicle;

    public FlightControlClient(Flyable vehicle) {
        this.vehicle = vehicle;
    }

    public void fly(double meters) {
        vehicle.flyToAltitude(meters);
    }
}