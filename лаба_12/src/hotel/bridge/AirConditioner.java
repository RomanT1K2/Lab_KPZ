package hotel.bridge;

import hotel.util.Logger;

/** CONCRETE IMPLEMENTOR: кондиціонер. */
public class AirConditioner implements Equipment {
    @Override
    public String getName() { return "Кондиціонер"; }

    @Override
    public void turnOn() { Logger.log(getName(), "увімкнено, охолодження до 22°C"); }

    @Override
    public void turnOff() { Logger.log(getName(), "вимкнено"); }
}
