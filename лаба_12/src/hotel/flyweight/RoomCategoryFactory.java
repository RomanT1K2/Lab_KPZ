package hotel.flyweight;

import hotel.util.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FLYWEIGHT FACTORY (фабрика легковаговиків).
 * Зберігає вже створені категорії в кеші (HashMap).
 * Якщо категорія вже є - повертає ІСНУЮЧИЙ об'єкт,
 * якщо ні - створює один раз і запам'ятовує.
 */
public class RoomCategoryFactory {

    // кеш: назва категорії -> об'єкт категорії
    private static final Map<String, RoomCategory> cache = new HashMap<>();

    public static RoomCategory getCategory(String name) {
        RoomCategory category = cache.get(name);
        if (category == null) {                 // ще не створювали
            category = createCategory(name);    // створюємо ОДИН раз
            cache.put(name, category);
            Logger.log("Фабрика категорій", "створено нову категорію: " + category);
        }
        return category;                        // повертаємо той самий об'єкт
    }

    // Імітація "бази даних" категорій готелю
    private static RoomCategory createCategory(String name) {
        switch (name) {
            case "Стандарт":
                return new RoomCategory(name, "Затишний номер з видом на двір", 18,
                        List.of("Wi-Fi", "Телевізор"), List.of("std1.jpg", "std2.jpg"), 1800);
            case "Комфорт":
                return new RoomCategory(name, "Номер з балконом і видом на гори", 26,
                        List.of("Wi-Fi", "Телевізор", "Міні-бар", "Балкон"),
                        List.of("cmf1.jpg", "cmf2.jpg", "cmf3.jpg"), 2600);
            case "Люкс":
                return new RoomCategory(name, "Двокімнатний люкс з каміном", 45,
                        List.of("Wi-Fi", "Телевізор", "Міні-бар", "Камін", "Джакузі"),
                        List.of("lux1.jpg", "lux2.jpg", "lux3.jpg", "lux4.jpg"), 4800);
            case "Сімейний":
                return new RoomCategory(name, "Просторий номер для родини з дітьми", 38,
                        List.of("Wi-Fi", "Телевізор", "Дитяче ліжко", "Кухонна зона"),
                        List.of("fam1.jpg", "fam2.jpg"), 3500);
            default:
                throw new IllegalArgumentException("Невідома категорія: " + name);
        }
    }

    public static int getCreatedCount() {
        return cache.size();
    }
}
