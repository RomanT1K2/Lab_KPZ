package hotel;

import hotel.booking.*;
import hotel.common.Log;
import hotel.confirmation.*;
import hotel.housekeeping.*;
import java.time.LocalDate;
import java.util.List;

/** Клас-раннер: окремий метод для кожної проблемної ситуації. */
public final class Main {

    public static void main(String[] args) {
        situation1RoomPreparation();
        situation2BookingCreation();
        situation3BookingConfirmation();
    }

    private static void header(String title) {
        System.out.println("\n==================== " + title + " ====================");
    }

    // ---------- Завдання 1: Abstract Factory ----------
    private static void situation1RoomPreparation() {
        header("Завдання 1. Підготовка номера (Abstract Factory)");
        HousekeepingService service = new HousekeepingService();

        // Один і той самий код служби, різні фабрики
        System.out.println(service.prepare(new Room("101", "стандарт"), new StandardKitFactory()));
        System.out.println(service.prepare(new Room("301", "люкс"), new LuxuryKitFactory()));
        System.out.println(service.prepare(new Room("205", "сімейний"), new FamilyKitFactory()));

        // Спроба змішати категорії (як на пілоті) — відхиляється
        try {
            service.prepare(new Room("102", "стандарт"), new LuxuryKitFactory());
        } catch (IllegalArgumentException e) {
            Log.event("Покоївки", "Відхилено: " + e.getMessage());
        }

        Log.event("Демо", "Додаємо категорію «шале» без змін у HousekeepingService");
        System.out.println(service.prepare(new Room("501", "шале"), new ChaletKitFactory()));
    }

    // ---------- Завдання 2: Builder ----------
    private static void situation2BookingCreation() {
        header("Завдання 2. Оформлення бронювання (Builder)");
        Guest guest = new Guest("Олена Коваль", "olena@example.com");
        LocalDate arrival = LocalDate.of(2026, 12, 20);

        try {
            Booking luxe = Booking.builder()
                    .guest(guest)
                    .category(RoomCategory.LUXURY)
                    .checkIn(arrival)
                    .checkOut(arrival.plusDays(5))
                    .withBreakfast()
                    .withTransfer()
                    .withBabyCot()
                    .build();
            System.out.println(luxe);

            Booking weekend = new BookingDirector().weekend(Booking.builder(), guest, LocalDate.of(2026, 12, 11));
            System.out.println(weekend);
        } catch (BookingValidationException e) {
            Log.event("Бронювання", "Неможливо сформувати бронювання: " + e.getMessage());
        }

        // Некоректні дати: виїзд раніше заїзду
        try {
            Booking.builder()
                    .guest(guest)
                    .category(RoomCategory.STANDARD)
                    .checkIn(arrival)
                    .checkOut(arrival.minusDays(3))
                    .build();
        } catch (BookingValidationException e) {
            Log.event("Бронювання", "Неможливо сформувати бронювання: " + e.getMessage());
        }

        // Бронювання без гостя
        try {
            Booking.builder().category(RoomCategory.FAMILY).checkIn(arrival).checkOut(arrival.plusDays(2)).build();
        } catch (BookingValidationException e) {
            Log.event("Бронювання", "Неможливо сформувати бронювання: " + e.getMessage());
        }
    }

    // ---------- Завдання 3: Factory Method ----------
    private static void situation3BookingConfirmation() {
        header("Завдання 3. Підтвердження бронювання (Factory Method)");
        RoomInventory inventory = new RoomInventory(5);
        Guest guest = new Guest("Іван Петренко", "ivan@example.com");
        LocalDate arrival = LocalDate.of(2027, 1, 8);

        Booking booking;
        try {
            booking = Booking.builder().guest(guest).category(RoomCategory.FAMILY)
                    .checkIn(arrival).checkOut(arrival.plusDays(4)).withBreakfast().build();
        } catch (BookingValidationException e) {
            Log.event("Бронювання", "Неможливо сформувати бронювання: " + e.getMessage());
            return;
        }

        // Існуючі канали — жодних if/switch за каналом
        List<ReservationChannel> channels = List.of(
                new ReceptionChannel(inventory), new WebsiteChannel(inventory), new TourOperatorChannel(inventory));
        for (ReservationChannel channel : channels) {
            channel.confirm(booking);
        }

        Log.event("Демо", "Підключаємо сервіс онлайн-бронювання новими класами без змін загального процесу");
        new OnlineBookingChannel(inventory).confirm(booking);
    }
}
   