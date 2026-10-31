package variant5_vehiclecontrolsystem;

public class CityCar implements EngineControllable, Acceleratable {

    @Override
    public void startEngine() {
        System.out.println("Car engine started.");
    }

    @Override
    public void stopEngine() {
        System.out.println("Car engine stopped.");
    }

    @Override
    public void accelerate(double speed) {
        System.out.println("Car accelerated to " + speed + " km/h.");
    }
}