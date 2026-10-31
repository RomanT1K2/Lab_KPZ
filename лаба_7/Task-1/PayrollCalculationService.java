package variant5_payrollsystem;

import java.math.BigDecimal;
import java.time.YearMonth;

public class PayrollCalculationService {

    private static final BigDecimal PIT_RATE =
            new BigDecimal("0.18");

    private static final BigDecimal MILITARY_RATE =
            new BigDecimal("0.015");

    public PayrollResult calculate(
            EmployeePayrollData emp,
            YearMonth payrollMonth
    ) {
        // Основна заробітна плата
        BigDecimal baseSalary = emp.getBaseHourlyRate()
                .multiply(BigDecimal.valueOf(emp.getHoursWorked()));

        // Загальна нарахована сума
        BigDecimal grossSalary = baseSalary.add(emp.getBonusAmount());

        // Розрахунок податків
        BigDecimal pitTax = grossSalary.multiply(PIT_RATE);

        BigDecimal militaryTax = grossSalary.multiply(MILITARY_RATE);

        BigDecimal totalTaxes = pitTax.add(militaryTax);

        // Зарплата до виплати
        BigDecimal netSalary = grossSalary.subtract(totalTaxes);

        // Повертаємо результат у вигляді окремого об'єкта
        return new PayrollResult(
                emp,
                payrollMonth,
                baseSalary,
                grossSalary,
                pitTax,
                militaryTax,
                totalTaxes,
                netSalary
        );
    }
}