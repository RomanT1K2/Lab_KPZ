package variant5_inventorystockmanager;

public class Main {

    public static void main(String[] args) {

        StockAdjustment adjustment = new StockAdjustment();

        adjustment.setItemSku("SKU-12345");
        adjustment.setWarehouseZone("A-12");
        adjustment.setQuantityDelta(10);


        // Production-реалізація
        System.out.println("=== PRODUCTION MODE ===");

        InventoryStockManager productionManager =
            new InventoryStockManager(
                new MongoInventoryRepository(),
                new MqttWarehouseBeaconClient(),
                new JsonFileReportLogger()
            );

        productionManager.adjustStockLevel(adjustment);


        // Mock-реалізація
        System.out.println("\n=== TEST MODE ===");

        MockInventoryRepository mockRepository =
            new MockInventoryRepository();

        InventoryStockManager testManager =
            new InventoryStockManager(
                mockRepository,
                new MqttWarehouseBeaconClient(),
                new JsonFileReportLogger()
            );

        testManager.adjustStockLevel(adjustment);

        System.out.println(
            "\nТестовий залишок: " + mockRepository.getStock("SKU-12345")
        );
    }
}