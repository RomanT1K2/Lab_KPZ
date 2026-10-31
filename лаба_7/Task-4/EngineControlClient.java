package variant5_vehiclecontrolsystem;

public class EngineControlClient {

    private final EngineControllable vehicle;

    public EngineControlClient(EngineControllable vehicle) {
        this.vehicle = vehicle;
    }

    public void start() {
        vehicle.startEngine();
    }

    public void stop() {
        vehicle.stopEngine();
    }
}