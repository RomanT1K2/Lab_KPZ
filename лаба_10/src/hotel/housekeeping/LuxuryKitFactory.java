package hotel.housekeeping;

/** Concrete Factory: комплект категорії «люкс». */
public final class LuxuryKitFactory implements RoomKitFactory {
    private static final String CATEGORY = "люкс";

    @Override public String category() { return CATEGORY; }
    @Override public Cosmetics createCosmetics() { return new LuxuryCosmetics(); }
    @Override public Minibar createMinibar() { return new LuxuryMinibar(); }
    @Override public Textile createTextile() { return new LuxuryTextile(); }

    public static final class LuxuryCosmetics implements Cosmetics {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "преміум-набір: гель, шампунь, крем, халат"; }
    }
    public static final class LuxuryMinibar implements Minibar {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "шампанське, вино, снеки, вода"; }
        @Override public double price() { return 950.0; }
    }
    public static final class LuxuryTextile implements Textile {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "рушники 6 шт., білизна єгипетська бавовна, пледи"; }
    }
}
