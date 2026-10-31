package variant5_vehiclecontrolsystem;

public class Main {

    public static void main(String[] args) {

        CityCar car = new CityCar();
        CargoDrone drone = new CargoDrone();

        // Керування автомобілем
        EngineControlClient carEngine = new EngineControlClient(car);
        SpeedControlClient carSpeed = new SpeedControlClient(car);

        carEngine.start();
        carSpeed.accelerate(80);
        carEngine.stop();

        System.out.println("----------------");

        // Керування дроном
        EngineControlClient droneEngine = new EngineControlClient(drone);
        SpeedControlClient droneSpeed = new SpeedControlClient(drone);
        FlightControlClient droneFlight = new FlightControlClient(drone);

        droneEngine.start();
        droneSpeed.accelerate(50);
        droneFlight.fly(100);
        droneEngine.stop();
    }
}