package hotel.chain;

import hotel.common.Journal;

/** Технічна служба: несправності. */
public class TechService extends RequestHandler {
    public TechService() {
        super("Техслужба");
    }

    @Override
    protected boolean canHandle(GuestRequest r) {
        return r.getType() == RequestType.MALFUNCTION;
    }

    @Override
    protected void process(GuestRequest r) {
        Journal.log(name, "ОБРОБИВ: майстер їде в номер " + r.getRoom() + " усувати несправність");
    }
}
