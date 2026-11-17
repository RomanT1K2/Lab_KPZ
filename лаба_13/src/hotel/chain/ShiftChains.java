package hotel.chain;

/** Збирає ланцюг обробників під потрібну зміну. */
public class ShiftChains {

    // Денна зміна: Рецепція -> Покоївки -> Техслужба -> Менеджер
    public static RequestHandler dayShift() {
        RequestHandler reception = new Reception(false);
        reception.setNext(new Housekeeping())
                 .setNext(new TechService())
                 .setNext(new DutyManager());
        return reception;
    }

    // Нічна зміна: покоївок немає. Рецепція (приймає прибирання "на ранок") -> Техслужба -> Менеджер
    public static RequestHandler nightShift() {
        RequestHandler reception = new Reception(true);
        reception.setNext(new TechService())
                 .setNext(new DutyManager());
        return reception;
    }
}
