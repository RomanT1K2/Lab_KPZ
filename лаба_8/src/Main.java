package variant5_vehiclerental;

import java.util.Map;
import variant5_vehiclerental.application.InsurancePlanRegistry;
import variant5_vehiclerental.application.RentalAgreementFactory;
import variant5_vehiclerental.application.RentalController;
import variant5_vehiclerental.application.RentalRegistrar;
import variant5_vehiclerental.application.VehicleClassRegistry;
import variant5_vehiclerental.cli.ConsoleCommandParser;
import variant5_vehiclerental.cli.RentalConsoleApp;
import variant5_vehiclerental.cli.VoucherFormatter;
import variant5_vehiclerental.domain.BasicInsurance;
import variant5_vehiclerental.domain.BusinessPolicy;
import variant5_vehiclerental.domain.EconomyPolicy;
import variant5_vehiclerental.domain.FullInsurance;
import variant5_vehiclerental.domain.SuvPolicy;
import variant5_vehiclerental.infrastructure.ConsoleSmsGateway;
import variant5_vehiclerental.infrastructure.InMemoryRentalCatalog;
import variant5_vehiclerental.infrastructure.InMemoryRentalRepository;

/** Composition Root: єдине місце, яке знає про ВСІ конкретні класи й «склеює» систему. */
public final class Main {
    public static void main(String[] args) {
        VehicleClassRegistry classes = new VehicleClassRegistry(Map.of(
                "Economy", new EconomyPolicy(),
                "Business", new BusinessPolicy(),
                "SUV", new SuvPolicy()));
        InsurancePlanRegistry plans = new InsurancePlanRegistry(Map.of(
                "Basic", new BasicInsurance(),
                "Full", new FullInsurance()));

        RentalController controller = new RentalController(
                new RentalAgreementFactory(new InMemoryRentalCatalog(classes), plans),
                new RentalRegistrar(new InMemoryRentalRepository(), new ConsoleSmsGateway()));

        new RentalConsoleApp(controller, new ConsoleCommandParser(), new VoucherFormatter()).run();
    }
}
