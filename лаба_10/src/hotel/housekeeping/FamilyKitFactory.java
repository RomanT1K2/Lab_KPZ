package hotel.housekeeping;

/** Concrete Factory: комплект категорії «сімейний». */
public final class FamilyKitFactory implements RoomKitFactory {
    private static final String CATEGORY = "сімейний";

    @Override public String category() { return CATEGORY; }
    @Override public Cosmetics createCosmetics() { return new FamilyCosmetics(); }
    @Override public Minibar createMinibar() { return new FamilyMinibar(); }
    @Override public Textile createTextile() { return new FamilyTextile(); }

    public static final class FamilyCosmetics implements Cosmetics {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "мило, шампунь, дитячий гель"; }
    }
    public static final class FamilyMinibar implements Minibar {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "вода, соки, дитяче печиво"; }
        @Override public double price() { return 400.0; }
    }
    public static final class FamilyTextile implements Textile {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "рушники 5 шт., білизна на 4 місця"; }
    }
}
