package variant5_payrollsystem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BankRegistryExporter {

    private static final String FILE_NAME =
            "bank_payout_registry.csv";

    public void export(PayrollResult result) {

        String monthYear = result.getPayrollMonth()
                .format(DateTimeFormatter.ofPattern("MM_yyyy"));

        String bankRegistryRow = String.format(
                Locale.US,
                "%s;%s;%.2f;UAH;Salary_%s%n",
                result.getTaxId(),
                result.getBankCardNumber(),
                result.getNetSalary(),
                monthYear
        );

        try (FileOutputStream fos =
                     new FileOutputStream(FILE_NAME, true)) {

            fos.write(bankRegistryRow.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            System.err.println(
                    "Помилка запису банківського реєстру: "
                            + e.getMessage()
            );
        }
    }
}