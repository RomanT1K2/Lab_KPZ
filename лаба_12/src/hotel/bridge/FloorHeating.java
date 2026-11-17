package hotel.bridge;

import hotel.util.Logger;

/**
 * НОВЕ обладнання - рівно ОДИН новий клас.
 * Жоден пристрій керування змінювати не потрібно.
 */
public class FloorHeating implements Equipment {
    @Override
    public String getName() { return "Тепла підлога"; }

    @Override
    public void turnOn() { Logger.log(getName(), "нагрівання підлоги до 28°C"); }

    @Override
    public void turnOff() { Logger.log(getName(), "нагрівання вимкнено"); }
}
