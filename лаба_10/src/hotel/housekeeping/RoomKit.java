package hotel.housekeeping;

/** Готовий комплект номера: три елементи однієї категорії. */
public final class RoomKit {
    private final String category;
    private final Cosmetics cosmetics;
    private final Minibar minibar;
    private final Textile textile;

    public RoomKit(String category, Cosmetics cosmetics, Minibar minibar, Textile textile) {
        this.category = category;
        this.cosmetics = cosmetics;
        this.minibar = minibar;
        this.textile = textile;
    }

    public String category() { return category; }
    public Cosmetics cosmetics() { return cosmetics; }
    public Minibar minibar() { return minibar; }
    public Textile textile() { return textile; }

    @Override public String toString() {
        return "Комплект «" + category + "»\n"
             + "    косметика: " + cosmetics.describe() + "\n"
             + "    мінібар:   " + minibar.describe() + " (" + minibar.price() + " грн)\n"
             + "    текстиль:  " + textile.describe();
    }
}
