package variant5_inventorystockmanager;

import java.util.HashMap;
import java.util.Map;

public class MockInventoryRepository implements InventoryRepository {

    private final Map<String, Integer> stock = new HashMap<>();

    @Override
    public void updateStock(String sku, int delta) {

        int newQuantity = stock.getOrDefault(sku, 0) + delta;

        stock.put(sku, newQuantity);

        System.out.println(
            "[Mock Storage] SKU '" + sku +
            "' має тестову кількість: " + newQuantity + " шт."
        );
    }

    public int getStock(String sku) {
        return stock.getOrDefault(sku, 0);
    }
}