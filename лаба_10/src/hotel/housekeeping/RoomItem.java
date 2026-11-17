package hotel.housekeeping;

/** Спільна абстракція будь-якого елемента комплекту номера. */
public interface RoomItem {
    String category();   // до якої категорії належить елемент
    String describe();   // з чого складається
}
