package hotel.chain;

import hotel.common.Journal;

/** Рецепція: рушники, будильник; уночі ще й приймає прибирання "на ранок". */
public class Reception extends RequestHandler {
    private final boolean nightMode; // true = нічна зміна

    public Reception(boolean nightMode) {
        super("Рецепція");
        this.nightMode = nightMode;
    }

    @Override
    protected boolean canHandle(GuestRequest r) {
        RequestType t = r.getType();
        return t == RequestType.TOWELS
                || t == RequestType.ALARM
                || (nightMode && t == RequestType.CLEANING);
    }

    @Override
    protected void process(GuestRequest r) {
        if (r.getType() == RequestType.CLEANING) {
            Journal.log(name, "ОБРОБИВ: прийняв прибирання для номера " + r.getRoom() + " з поміткою «на ранок»");
        } else {
            Journal.log(name, "ОБРОБИВ: виконано «" + r.getType().getTitle() + "» для номера " + r.getRoom());
        }
    }
}
