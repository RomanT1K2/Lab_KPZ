package variant5_payrollsystem;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Locale;

public class PayrollAuditLogger {

    private static final String FILE_NAME =
            "payroll_audit.log";

    public void log(PayrollResult result) {

        String logEntry = String.format(
                Locale.US,
                "[%s] Audit Payroll: EmpID=%s, Gross=%.2f, Net=%.2f, Taxes=%.2f%n",

                Instant.now().toString(),
                result.getEmployeeId(),
                result.getGrossSalary(),
                result.getNetSalary(),
                result.getTotalTaxes()
        );

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            writer.write(logEntry);

        } catch (IOException e) {
            System.err.println(
                    "Помилка логування аудиту: "
                            + e.getMessage()
            );
        }
    }
}