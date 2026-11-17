package hotel.chain;

import hotel.common.Journal;

/** Черговий менеджер: скарги та компенсації. */
public class DutyManager extends RequestHandler {
    public DutyManager() {
        super("Менеджер");
    }

    @Override
    protected boolean canHandle(GuestRequest r) {
        return r.getType() == RequestType.COMPLAINT
                || r.getType() == RequestType.COMPENSATION;
    }

    @Override
    protected void process(GuestRequest r) {
        Journal.log(name, "ОБРОБИВ: розглядає «" + r.getType().getTitle() + "» від номера " + r.getRoom());
    }
}
