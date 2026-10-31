package variant5_vehiclecontrolsystem;

public class SpeedControlClient {

    private final Acceleratable vehicle;

    public SpeedControlClient(Acceleratable vehicle) {
        this.vehicle = vehicle;
    }

    public void accelerate(double speed) {
        vehicle.accelerate(speed);
    }
}