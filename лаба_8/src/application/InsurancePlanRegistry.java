package variant5_vehiclerental.application;

import java.util.Map;
import java.util.TreeMap;
import variant5_vehiclerental.domain.InsurancePlan;

/** Pure Fabrication: словник «назва -> страховий план» замість if-else за рядком. */
public final class InsurancePlanRegistry {
    private final Map<String, InsurancePlan> plans = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public InsurancePlanRegistry(Map<String, InsurancePlan> source) {
        plans.putAll(source);
    }

    public InsurancePlan find(String name) {
        InsurancePlan plan = plans.get(name == null ? "" : name.trim());
        if (plan == null) {
            throw new IllegalArgumentException("Невідомий тип страховки: '" + name + "'. Доступні: " + plans.keySet());
        }
        return plan;
    }
}
