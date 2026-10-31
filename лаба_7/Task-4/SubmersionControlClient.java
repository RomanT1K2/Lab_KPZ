package variant5_vehiclecontrolsystem;

public class SubmersionControlClient {

    private final Submersible vehicle;

    public SubmersionControlClient(Submersible vehicle) {
        this.vehicle = vehicle;
    }

    public void submerge(double meters) {
        vehicle.submergeToDepth(meters);
    }
}   