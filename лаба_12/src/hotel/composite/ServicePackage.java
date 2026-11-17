package hotel.composite;

import hotel.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * COMPOSITE (компонувальник): пакет послуг.
 * Може містити і прості послуги, і інші пакети -
 * адже все це ServiceComponent.
 */
public class ServicePackage implements ServiceComponent {

    private final String name;
    private final List<ServiceComponent> children = new ArrayList<>();

    public ServicePackage(String name) {
        this.name = name;
    }

    public void add(ServiceComponent component) {
        children.add(component);
    }

    @Override
    public String getName() { return name; }

    /** Рекурсія: сума цін усіх дочірніх вузлів (вони самі порахують свою вкладеність). */
    @Override
    public double getPrice() {
        double sum = 0;
        for (ServiceComponent child : children) {
            sum += child.getPrice();
        }
        return sum;
    }

    @Override
    public List<String> getServiceNames() {
        List<String> names = new ArrayList<>();
        for (ServiceComponent child : children) {
            names.addAll(child.getServiceNames());
        }
        return names;
    }

    @Override
    public void print(String indent) {
        Logger.log("Дерево пакета", indent + "+ Пакет \"" + name + "\" (" + getPrice() + " грн)");
        for (ServiceComponent child : children) {
            child.print(indent + "    "); // відступ на рівень глибше
        }
    }
}
