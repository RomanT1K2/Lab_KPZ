package hotel.housekeeping;

/** Concrete Factory: комплект категорії «стандарт». */
public final class StandardKitFactory implements RoomKitFactory {
    private static final String CATEGORY = "стандарт";

    @Override public String category() { return CATEGORY; }
    @Override public Cosmetics createCosmetics() { return new StandardCosmetics(); }
    @Override public Minibar createMinibar() { return new StandardMinibar(); }
    @Override public Textile createTextile() { return new StandardTextile(); }

    public static final class StandardCosmetics implements Cosmetics {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "мило, шампунь 30 мл"; }
    }
    public static final class StandardMinibar implements Minibar {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "вода, сік"; }
        @Override public double price() { return 120.0; }
    }
    public static final class StandardTextile implements Textile {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "рушники 2 шт., постільна білизна бавовна"; }
    }
}
