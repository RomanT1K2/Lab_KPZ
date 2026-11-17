package hotel.bridge;

import hotel.util.Logger;

/**
 * ABSTRACTION (абстракція): "як подається команда".
 * МІСТ - це поле equipment: пристрій не знає, яке саме обладнання
 * за ним стоїть, і викликає лише методи інтерфейсу Equipment.
 */
public abstract class ControlDevice {

    protected Equipment equipment; // <-- місток до ієрархії обладнання

    public ControlDevice(Equipment equipment) {
        this.equipment = equipment;
    }

    /** Під'єднати інше обладнання під час роботи програми. */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
        Logger.log(getDeviceName(), "тепер керує обладнанням: " + equipment.getName());
    }

    /** Кожен пристрій по-своєму "подає" команду. */
    protected abstract String getDeviceName();
    protected abstract String describeSignal(String command);

    public void turnOn() {
        Logger.log(getDeviceName(), describeSignal("команда ON для обладнання «" + equipment.getName() + "»"));
        equipment.turnOn();
    }

    public void turnOff() {
        Logger.log(getDeviceName(), describeSignal("команда OFF для обладнання «" + equipment.getName() + "»"));
        equipment.turnOff();
    }
}
