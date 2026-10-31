package variant5_inventorystockmanager;

import java.util.Objects;

public class InventoryStockManager {

    private final InventoryRepository inventoryRepository;
    private final WarehouseBeacon beaconClient;
    private final StockEventLogger jsonLogger;

    public InventoryStockManager(
            InventoryRepository inventoryRepository,
            WarehouseBeacon beaconClient,
            StockEventLogger jsonLogger) {

        this.inventoryRepository = Objects.requireNonNull(inventoryRepository);
        this.beaconClient = Objects.requireNonNull(beaconClient);
        this.jsonLogger = Objects.requireNonNull(jsonLogger);
    }

    public void adjustStockLevel(StockAdjustment adjustment) {

        if (adjustment == null ||
                adjustment.getItemSku() == null ||
                adjustment.getItemSku().isEmpty()) {

            throw new IllegalArgumentException(
                "Некоректні дані коригування товару."
            );
        }

        inventoryRepository.updateStock(
            adjustment.getItemSku(),
            adjustment.getQuantityDelta()
        );

        beaconClient.signalShelfLight(
            adjustment.getWarehouseZone(),
            adjustment.getItemSku()
        );

        String jsonMessage =
            "{\"sku\":\"" + adjustment.getItemSku() +
            "\", \"delta\":" + adjustment.getQuantityDelta() + "}";

        jsonLogger.logStockEvent(jsonMessage);
    }
}