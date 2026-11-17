package hotel.confirmation;

/** Product: підтвердження бронювання. */
public interface Confirmation {
    String render();   // вигляд підтвердження
    void send();       // доставка своїм каналом
}
