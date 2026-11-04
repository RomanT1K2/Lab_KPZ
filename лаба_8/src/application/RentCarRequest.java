package variant5_vehiclerental.application;

/** DTO-запит від консольного шару до контролера. Жодної логіки, лише дані. */
public record RentCarRequest(String clientName, String carClass, int days, String insuranceType) {
}
