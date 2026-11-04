package variant5_vehiclerental.cli;

import variant5_vehiclerental.application.RentCarRequest;

/** Pure Fabrication: розбір «сирих» рядків з консолі в DTO. Без Scanner та без бізнес-правил. */
public final class ConsoleCommandParser {
    private static final int DEFAULT_DAYS = 5;            // як у Legacy
    private static final String DEFAULT_INSURANCE = "Full"; // як у Legacy

    public RentCarRequest parse(String name, String carClass, String daysText, String insuranceText) {
        int days = daysText.isBlank() ? DEFAULT_DAYS : parseDays(daysText);
        String insurance = insuranceText.isBlank() ? DEFAULT_INSURANCE : insuranceText.trim();
        return new RentCarRequest(name.trim(), carClass.trim(), days, insurance);
    }

    private int parseDays(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Кількість діб має бути цілим числом");
        }
    }
}
