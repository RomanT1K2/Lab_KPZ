package hotel.bridge;

/** REFINED ABSTRACTION: мобільний застосунок гостя. */
public class MobileApp extends ControlDevice {
    public MobileApp(Equipment equipment) { super(equipment); }

    @Override
    protected String getDeviceName() { return "Застосунок"; }

    @Override
    protected String describeSignal(String command) { return "запит через Wi-Fi: " + command; }
}
