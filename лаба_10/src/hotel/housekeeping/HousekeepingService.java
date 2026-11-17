package hotel.housekeeping;

import hotel.common.Log;
import java.util.stream.Stream;

/**
 * Client. Працює ЛИШЕ з абстракціями (RoomKitFactory, Cosmetics, Minibar, Textile) —
 * жодного конкретного класу категорії тут немає.
 */
public final class HousekeepingService {
    private static final String SRC = "Покоївки";

    public RoomKit prepare(Room room, RoomKitFactory factory) {
        if (!room.category().equals(factory.category())) {
            throw new IllegalArgumentException("Номер " + room.number() + " має категорію «" + room.category()
                    + "», а фабрика комплектує «" + factory.category() + "»");
        }
        Log.event(SRC, "Комплектація номера " + room.number() + " (категорія «" + room.category() + "»)");

        Cosmetics cosmetics = factory.createCosmetics();
        Log.event(SRC, "Косметика: " + cosmetics.describe() + " [" + cosmetics.category() + "]");
        Minibar minibar = factory.createMinibar();
        Log.event(SRC, "Мінібар: " + minibar.describe() + " [" + minibar.category() + "]");
        Textile textile = factory.createTextile();
        Log.event(SRC, "Текстиль: " + textile.describe() + " [" + textile.category() + "]");

        boolean consistent = Stream.of(cosmetics, minibar, textile)
                .map(RoomItem::category).allMatch(room.category()::equals);
        Log.event(SRC, consistent
                ? "Усі елементи комплекту належать до категорії «" + room.category() + "»"
                : "ПОМИЛКА: елементи комплекту різних категорій");

        RoomKit kit = new RoomKit(factory.category(), cosmetics, minibar, textile);
        room.assign(kit);
        return kit;
    }
}
