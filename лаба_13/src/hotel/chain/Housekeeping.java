package hotel.chain;

import hotel.common.Journal;

/** Служба покоївок: прибирання (працює лише вдень). */
public class Housekeeping extends RequestHandler {
    public Housekeeping() {
        super("Покоївки");
    }

    @Override
    protected boolean canHandle(GuestRequest r) {
        return r.getType() == RequestType.CLEANING;
    }

    @Override
    protected void process(GuestRequest r) {
        Journal.log(name, "ОБРОБИВ: покоївка прибирає номер " + r.getRoom());
    }
}
