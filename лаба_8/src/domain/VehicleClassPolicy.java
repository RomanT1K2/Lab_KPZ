package variant5_vehiclerental.domain;

/**
 * Стабільний контракт (GRASP Protected Variations) для надбавки за клас авто.
 * Нові класи (Premium, Van ...) додаються новою реалізацією, без правки існуючого коду.
 */
public interface VehicleClassPolicy {
    /** Повертає вартість оренди з урахуванням коефіцієнта класу авто. */
    double applyMarkup(double baseCost);
}
