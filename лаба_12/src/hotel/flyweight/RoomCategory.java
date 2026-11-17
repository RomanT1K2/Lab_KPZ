package hotel.flyweight;

import java.util.List;

/**
 * FLYWEIGHT (легковаговик).
 * Містить СПІЛЬНІ (внутрішні) дані категорії номера.
 * Об'єкт незмінний (поля final), тому його безпечно
 * використовувати одразу в тисячах записів календаря.
 */
public class RoomCategory {

    private final String name;
    private final String description;
    private final double area;            // площа, м²
    private final List<String> amenities; // зручності
    private final List<String> photos;    // назви файлів фото
    private final double basePrice;       // базова ціна за ніч

    public RoomCategory(String name, String description, double area,
                        List<String> amenities, List<String> photos, double basePrice) {
        this.name = name;
        this.description = description;
        this.area = area;
        this.amenities = amenities;
        this.photos = photos;
        this.basePrice = basePrice;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getArea() { return area; }
    public List<String> getAmenities() { return amenities; }
    public List<String> getPhotos() { return photos; }
    public double getBasePrice() { return basePrice; }

    @Override
    public String toString() {
        return name + " (" + area + " м², " + basePrice + " грн/ніч)";
    }
}
