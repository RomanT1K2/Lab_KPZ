package hotel.housekeeping;

/** Нова категорія «шале»: додається ЛИШЕ новим файлом, HousekeepingService не змінюється. */
public final class ChaletKitFactory implements RoomKitFactory {
    private static final String CATEGORY = "шале";

    @Override public String category() { return CATEGORY; }
    @Override public Cosmetics createCosmetics() { return new ChaletCosmetics(); }
    @Override public Minibar createMinibar() { return new ChaletMinibar(); }
    @Override public Textile createTextile() { return new ChaletTextile(); }

    public static final class ChaletCosmetics implements Cosmetics {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "органічна косметика, халати"; }
    }
    public static final class ChaletMinibar implements Minibar {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "карпатський чай, мед, настоянки"; }
        @Override public double price() { return 700.0; }
    }
    public static final class ChaletTextile implements Textile {
        @Override public String category() { return CATEGORY; }
        @Override public String describe() { return "вовняні пледи, лляна білизна"; }
    }
}
