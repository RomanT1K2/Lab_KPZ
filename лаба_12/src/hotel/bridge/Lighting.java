package hotel.bridge;

import hotel.util.Logger;

/** CONCRETE IMPLEMENTOR: освітлення. */
public class Lighting implements Equipment {
    @Override
    public String getName() { return "Освітлення"; }

    @Override
    public void turnOn() { Logger.log(getName(), "світло увімкнено"); }

    @Override
    public void turnOff() { Logger.log(getName(), "світло вимкнено"); }
}
