package hotel.housekeeping;

/** Abstract Factory: створює УЗГОДЖЕНУ родину продуктів однієї категорії. */
public interface RoomKitFactory {
    String category();
    Cosmetics createCosmetics();
    Minibar createMinibar();
    Textile createTextile();
}
