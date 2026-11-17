package hotel.bridge;

/**
 * IMPLEMENTOR (реалізація): "що виконує команду".
 * Для штор turnOn = відкрити, turnOff = закрити.
 */
public interface Equipment {
    String getName();
    void turnOn();
    void turnOff();
}
