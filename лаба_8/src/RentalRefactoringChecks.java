package variant5_vehiclerental;

import java.util.Map;
import variant5_vehiclerental.application.*;
import variant5_vehiclerental.domain.*;
import variant5_vehiclerental.infrastructure.*;

/** Прості перевірки без JUnit: запуск `java -cp out variant5_vehiclerental.RentalRefactoringChecks`. */
public final class RentalRefactoringChecks {
    private static int failed = 0;

    private static void check(String name, boolean ok) {
        System.out.println((ok ? "[OK]   " : "[FAIL] ") + name);
        if (!ok) failed++;
    }

    private static RentalController controller() {
        VehicleClassRegistry classes = new VehicleClassRegistry(Map.of(
                "Economy", new EconomyPolicy(), "Business", new BusinessPolicy(), "SUV", new SuvPolicy()));
        InsurancePlanRegistry plans = new InsurancePlanRegistry(Map.of(
                "Basic", new BasicInsurance(), "Full", new FullInsurance()));
        return new RentalController(
                new RentalAgreementFactory(new InMemoryRentalCatalog(classes), plans),
                new RentalRegistrar(new InMemoryRentalRepository(), new ConsoleSmsGateway()));
    }

    private static boolean throwsIae(Runnable r) {
        try { r.run(); return false; } catch (IllegalArgumentException e) { return true; }
    }

    public static void main(String[] args) {
        RentalController c = controller();

        RentCarResponse suv = c.rent(new RentCarRequest("Іван", "SUV", 5, "Full"));
        check("SUV/5/Full: оренда 10800", suv.rentalTotal() == 10800.0);
        check("SUV/5/Full: страховка 2500", suv.insuranceTotal() == 2500.0);
        check("SUV/5/Full: застава 5000 (стаж 4 роки)", suv.depositAmount() == 5000.0);
        check("SUV/5/Full: разом 13300", suv.finalPrice() == 13300.0);
        check("id договору призначено репозиторієм (9012)", suv.rentalId() == 9012);

        check("Business: оренда 11700", c.rent(new RentCarRequest("А", "Business", 5, "Full")).rentalTotal() == 11700.0);
        check("Economy: оренда 9000", c.rent(new RentCarRequest("А", "Economy", 5, "Full")).rentalTotal() == 9000.0);
        check("Basic: страховка 1000", c.rent(new RentCarRequest("А", "Economy", 5, "Basic")).insuranceTotal() == 1000.0);
        check("регістр не важливий (suv)", c.rent(new RentCarRequest("А", "suv", 5, "full")).rentalTotal() == 10800.0);

        Vehicle car = new Vehicle(1, "Test", new EconomyPolicy(), 1000);
        InsurancePlan ins = new BasicInsurance();
        RentalAgreement lowExp = RentalAgreement.create(new Client(1, "Новачок", 2, "+380"), car, 1, ins);
        check("стаж 2 роки: застава 8000", lowExp.depositAmount() == 8000.0);
        RentalAgreement edge = RentalAgreement.create(new Client(1, "Межа", 3, "+380"), car, 1, ins);
        check("стаж рівно 3 роки: застава 5000", edge.depositAmount() == 5000.0);

        check("невідомий клас авто -> помилка", throwsIae(() -> c.rent(new RentCarRequest("А", "Truck", 5, "Full"))));
        check("невідома страховка -> помилка", throwsIae(() -> c.rent(new RentCarRequest("А", "SUV", 5, "Gold"))));
        check("0 діб -> помилка", throwsIae(() -> RentalAgreement.create(new Client(1, "А", 5, "+380"), car, 0, ins)));
        check("порожнє ім'я -> помилка", throwsIae(() -> new Client(1, " ", 5, "+380")));

        System.out.println(failed == 0 ? "\nУСІ ПЕРЕВІРКИ ПРОЙДЕНО" : "\nПровалено: " + failed);
        System.exit(failed == 0 ? 0 : 1);
    }
}
