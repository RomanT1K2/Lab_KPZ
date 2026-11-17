package hotel.bridge;

/** REFINED ABSTRACTION: настінна панель. */
public class WallPanel extends ControlDevice {
    public WallPanel(Equipment equipment) { super(equipment); }

    @Override
    protected String getDeviceName() { return "Настінна панель"; }

    @Override
    protected String describeSignal(String command) { return "натиснуто кнопку: " + command; }
}
