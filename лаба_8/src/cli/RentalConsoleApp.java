package variant5_vehiclerental.cli;

import java.util.Scanner;
import variant5_vehiclerental.application.RentCarRequest;
import variant5_vehiclerental.application.RentalController;

/**
 * Thin Console UI: тільки читає з консолі, друкує і передає DTO контролеру.
 * Жодних обчислень і жодних доменних класів.
 */
public final class RentalConsoleApp {
    private final RentalController controller;
    private final ConsoleCommandParser parser;
    private final VoucherFormatter formatter;

    public RentalConsoleApp(RentalController controller, ConsoleCommandParser parser, VoucherFormatter formatter) {
        this.controller = controller;
        this.parser = parser;
        this.formatter = formatter;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("=== Оформлення оренди автомобіля ===");
        while (true) {
            System.out.print("Введіть ім'я клієнта: ");
            String name = in.nextLine();
            System.out.print("Введіть клас авто (Economy/Business/SUV): ");
            String carClass = in.nextLine();
            System.out.print("Кількість діб (Enter = 5): ");
            String days = in.nextLine();
            System.out.print("Страховка Basic/Full (Enter = Full): ");
            String insurance = in.nextLine();
            try {
                RentCarRequest request = parser.parse(name, carClass, days, insurance);
                System.out.println(formatter.format(controller.rent(request)));
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: " + e.getMessage() + ". Спробуйте ще раз.\n");
            }
        }
    }
}
