package hotel.chain;

import hotel.common.Journal;

/**
 * Приймає запити гостей і віддає їх першій ланці ланцюга (Client).
 * Ланцюг можна змінити під час роботи програми (setChain).
 */
public class RequestDesk {
    private RequestHandler firstHandler;

    public void setChain(RequestHandler first) {
        this.firstHandler = first;
    }

    public void submit(GuestRequest request) {
        Journal.log("Гість", "подав " + request);
        boolean handled = firstHandler.handle(request);
        if (!handled) {
            // Запит не губиться: гість отримує зрозуміле повідомлення
            Journal.log("Система", "Жоден рівень не зміг обробити запит. Повідомлення гостю номера "
                    + request.getRoom() + ": «Вибачте, ми не можемо виконати цей запит. "
                    + "Зверніться, будь ласка, до рецепції особисто.»");
        }
    }
}
