package variant5_vehiclerental.application;

import java.util.Map;
import java.util.TreeMap;
import variant5_vehiclerental.domain.VehicleClassPolicy;

/**
 * Pure Fabrication + Dictionary Dispatch: єдине місце, де рядок "SUV" перетворюється на об'єкт.
 * Замінює каскад if-else ("Business".equalsIgnoreCase ... else if "SUV" ...).
 */
public final class VehicleClassRegistry {
    private final Map<String, VehicleClassPolicy> policies = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public VehicleClassRegistry(Map<String, VehicleClassPolicy> source) {
        policies.putAll(source);
    }

    public VehicleClassPolicy find(String name) {
        VehicleClassPolicy policy = policies.get(name == null ? "" : name.trim());
        if (policy == null) {
            throw new IllegalArgumentException("Невідомий клас авто: '" + name + "'. Доступні: " + policies.keySet());
        }
        return policy;
    }
}
