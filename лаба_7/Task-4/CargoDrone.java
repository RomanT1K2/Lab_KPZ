package variant5_vehiclecontrolsystem;

public class CargoDrone implements EngineControllable, Acceleratable, Flyable {

    @Override
    public void startEngine() {
        System.out.println("Drone rotors armed.");
    }

    @Override
    public void stopEngine() {
        System.out.println("Drone rotors disarmed.");
    }

    @Override
    public void accelerate(double speed) {
        System.out.println("Drone speed set to " + speed + " km/h.");
    }

    @Override
    public void flyToAltitude(double meters) {
        System.out.println("Flying at " + meters + " meters altitude.");
    }
}