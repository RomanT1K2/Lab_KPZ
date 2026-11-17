package hotel.command;

/** Command: операція, яка вміє виконатися і скасуватися (сторнуватися). */
public interface Command {
    void execute(); // виконати нарахування
    void undo();    // сторнувати (додати зворотну операцію)
}
