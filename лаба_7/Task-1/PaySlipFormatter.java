package variant5_payrollsystem;

import java.util.Locale;

public class PaySlipFormatter {

    public String format(PayrollResult result) {

        return String.format(
                Locale.US,
                "--------------------------------------------------%n" +
                "РОЗРАХУНКОВИЙ ЛИСТОК ЗА МІСЯЦЬ%n" +
                "Співробітник: %s (ІПН: %s)%n" +
                "Відпрацьовано годин: %d год%n" +
                "--------------------------------------------------%n" +
                "Нараховано (Gross): %.2f грн%n" +
                "- Основна оплата: %.2f грн%n" +
                "- Премія: %.2f грн%n" +
                "Утримано податків: %.2f грн%n" +
                "- ПДФО (18%%): %.2f грн%n" +
                "- Військовий збір (1.5%%): %.2f грн%n" +
                "--------------------------------------------------%n" +
                "ДО ВИПЛАТИ (Net): %.2f грн%n" +
                "--------------------------------------------------",

                result.getFullName(),
                result.getTaxId(),
                result.getHoursWorked(),

                result.getGrossSalary(),
                result.getBaseSalary(),
                result.getBonusAmount(),

                result.getTotalTaxes(),
                result.getPitTax(),
                result.getMilitaryTax(),

                result.getNetSalary()
        );
    }
}