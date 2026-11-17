package hotel.composite;

import hotel.util.Logger;

import java.util.List;

/** LEAF (лист): окрема послуга, нічого в собі не містить. */
public class SingleService implements ServiceComponent {

    private final String name;
    private final double price;

    public SingleService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }

    @Override
    public List<String> getServiceNames() {
        return List.of(name); // лист повертає лише себе
    }

    @Override
    public void print(String indent) {
        Logger.log("Дерево пакета", indent + "- " + name + " (" + price + " грн)");
    }
}
