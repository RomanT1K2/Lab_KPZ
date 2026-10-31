package variant5_payrollsystem;

import java.math.BigDecimal;
import java.time.YearMonth;

public class PayrollResult {
    private final String employeeId;
    private final String fullName;
    private final String taxId;
    private final String bankCardNumber;

    private final int hoursWorked;
    private final YearMonth payrollMonth;

    private final BigDecimal baseSalary;
    private final BigDecimal bonusAmount;
    private final BigDecimal grossSalary;

    private final BigDecimal pitTax;
    private final BigDecimal militaryTax;
    private final BigDecimal totalTaxes;
    private final BigDecimal netSalary;

    public PayrollResult(
            EmployeePayrollData emp,
            YearMonth payrollMonth,
            BigDecimal baseSalary,
            BigDecimal grossSalary,
            BigDecimal pitTax,
            BigDecimal militaryTax,
            BigDecimal totalTaxes,
            BigDecimal netSalary
    ) {
        this.employeeId = emp.getEmployeeId();
        this.fullName = emp.getFullName();
        this.taxId = emp.getTaxId();
        this.bankCardNumber = emp.getBankCardNumber();

        this.hoursWorked = emp.getHoursWorked();
        this.payrollMonth = payrollMonth;

        this.baseSalary = baseSalary;
        this.bonusAmount = emp.getBonusAmount();
        this.grossSalary = grossSalary;

        this.pitTax = pitTax;
        this.militaryTax = militaryTax;
        this.totalTaxes = totalTaxes;
        this.netSalary = netSalary;
    }

    public String getEmployeeId() { return employeeId; }
    public String getFullName() { return fullName; }
    public String getTaxId() { return taxId; }
    public String getBankCardNumber() { return bankCardNumber; }

    public int getHoursWorked() { return hoursWorked; }
    public YearMonth getPayrollMonth() { return payrollMonth; }

    public BigDecimal getBaseSalary() { return baseSalary; }
    public BigDecimal getBonusAmount() { return bonusAmount; }
    public BigDecimal getGrossSalary() { return grossSalary; }

    public BigDecimal getPitTax() { return pitTax; }
    public BigDecimal getMilitaryTax() { return militaryTax; }
    public BigDecimal getTotalTaxes() { return totalTaxes; }
    public BigDecimal getNetSalary() { return netSalary; }
}