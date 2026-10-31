package variant5_inventorystockmanager;

public class MongoInventoryRepository implements InventoryRepository {

    @Override
    public void updateStock(String sku, int delta) {
        System.out.println(
            "[MongoDB Storage] Залишок для SKU '" +
            sku + "' змінено на " + delta + " шт."
        );
    }
}