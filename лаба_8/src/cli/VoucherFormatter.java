package variant5_vehiclerental.cli;

import variant5_vehiclerental.application.RentCarResponse;

/** Pure Fabrication: форматування ваучера (текст — відповідальність UI, а не домену). */
public final class VoucherFormatter {
    public String format(RentCarResponse r) {
        return "Ваучер оренди #" + r.rentalId() +
                "\nКлієнт: " + r.clientName() +
                "\nАвто: " + r.vehicleModel() +
                "\nОренда: " + r.rentalTotal() + " грн" +
                "\nСтраховка: " + r.insuranceTotal() + " грн" +
                "\nЗастава: " + r.depositAmount() + " грн" +
                "\nЗагалом: " + r.finalPrice() + " грн";
    }
}
