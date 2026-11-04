package variant5_vehiclerental.domain;

/**
 * Клієнт прокату. Rich Domain Model:
 *  - усі поля private final (немає сеттерів) — стан змінити ззовні неможливо;
 *  - об'єкт одразу валідний (перевірка в конструкторі);
 *  - Information Expert: саме Client знає, чи його стаж малий.
 */
public final class Client {
    /** Мінімальний стаж, нижче якого діє підвищена застава (було: магічне число 3). */
    private static final int MIN_SAFE_EXPERIENCE_YEARS = 3;

    private final int id;
    private final String name;
    private final int driverExperienceYears;
    private final String phone;

    public Client(int id, String name, int driverExperienceYears, String phone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім'я клієнта не може бути порожнім");
        }
        if (driverExperienceYears < 0) {
            throw new IllegalArgumentException("Стаж водія не може бути від'ємним");
        }
        this.id = id;
        this.name = name.trim();
        this.driverExperienceYears = driverExperienceYears;
        this.phone = phone;
    }

    /** Information Expert: перевірка стажу водія — всередині власника даних. */
    public boolean hasLowExperience() {
        return driverExperienceYears < MIN_SAFE_EXPERIENCE_YEARS;
    }

    public int id() { return id; }
    public String name() { return name; }
    public String phone() { return phone; }
}
