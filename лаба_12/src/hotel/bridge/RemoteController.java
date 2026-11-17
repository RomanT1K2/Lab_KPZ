package hotel.bridge;

/** REFINED ABSTRACTION: пульт. */
public class RemoteController extends ControlDevice {
    public RemoteController(Equipment equipment) { super(equipment); }

    @Override
    protected String getDeviceName() { return "Пульт"; }

    @Override
    protected String describeSignal(String command) { return "інфрачервоний сигнал: " + command; }
}
