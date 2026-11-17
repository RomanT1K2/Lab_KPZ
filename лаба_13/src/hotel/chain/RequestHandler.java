package hotel.chain;

import hotel.common.Journal;

/**
 * Handler: базова ланка ланцюга.
 * Кожна ланка або обробляє запит, або передає наступній.
 */
public abstract class RequestHandler {
    protected final String name;     // назва рівня (для журналу)
    protected RequestHandler next;   // наступна ланка (може бути null)

    public RequestHandler(String name) {
        this.name = name;
    }

    // Повертає наступну ланку, щоб можна було будувати ланцюг "в рядок"
    public RequestHandler setNext(RequestHandler next) {
        this.next = next;
        return next;
    }

    /** @return true, якщо запит хтось обробив */
    public boolean handle(GuestRequest request) {
        Journal.log(name, "отримав " + request);
        if (canHandle(request)) {
            process(request);
            return true;
        }
        if (next != null) {
            Journal.log(name, "не обробляє цей тип, передає далі -> " + next.name);
            return next.handle(request);
        }
        return false; // ланцюг закінчився, ніхто не обробив
    }

    protected abstract boolean canHandle(GuestRequest request); // чи мій це запит?
    protected abstract void process(GuestRequest request);      // що робити з ним
}
