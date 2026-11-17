package hotel.housekeeping;

/** Абстрактний продукт №2: мінібар. Саме його вартість не мала потрапляти у «чужий» тариф. */
public interface Minibar extends RoomItem {
    double price();
}
