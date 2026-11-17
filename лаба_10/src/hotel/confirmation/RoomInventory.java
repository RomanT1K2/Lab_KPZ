package hotel.confirmation;

import hotel.booking.RoomCategory;
import java.util.EnumMap;
import java.util.Map;

/** Наявність номерів за категоріями. */
public final class RoomInventory {
    private final Map<RoomCategory, Integer> free = new EnumMap<>(RoomCategory.class);

    public RoomInventory(int perCategory) {
        for (RoomCategory c : RoomCategory.values()) free.put(c, perCategory);
    }

    public boolean isAvailable(RoomCategory c) { return free.get(c) > 0; }
    public void reserve(RoomCategory c) { free.merge(c, -1, Integer::sum); }
    public int left(RoomCategory c) { return free.get(c); }
}
