package variant5_payrollsystem;

import java.time.YearMonth;

public class PayrollProcessor {

    private final PayrollValidator validator;
    private final PayrollCalculationService calculator;
    private final PaySlipFormatter formatter;
    private final BankRegistryExporter exporter;
    private final PayrollAuditLogger auditLogger;
    private final ConsolePaySlipPrinter printer;

    public PayrollProcessor(
            PayrollValidator validator,
            PayrollCalculationService calculator,
            PaySlipFormatter formatter,
            BankRegistryExporter exporter,
            PayrollAuditLogger auditLogger,
            ConsolePaySlipPrinter printer
    ) {
        this.validator = validator;
        this.calculator = calculator;
        this.formatter = formatter;
        this.exporter = exporter;
        this.auditLogger = auditLogger;
        this.printer = printer;
    }

    public void calculateAndProcessSalary(EmployeePayrollData emp) {

        // 1. Валідація даних
        validator.validate(emp);

        // 2. Розрахунок заробітної плати
        PayrollResult result = calculator.calculate(
                emp,
                YearMonth.now()
        );

        // 3. Формування розрахункового листка
        String paySlip = formatter.format(result);

        // 4. Експорт банківського реєстру
        exporter.export(result);

        // 5. Логування бухгалтерської операції
        auditLogger.log(result);

        // 6. Виведення розрахункового листка
        printer.print(paySlip);
    }
}