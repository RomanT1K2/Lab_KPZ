package variant5_inventorystockmanager;

public interface InventoryRepository {
    void updateStock(String sku, int delta);
}