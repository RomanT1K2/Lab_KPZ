package variant5_inventorystockmanager;

public class MqttWarehouseBeaconClient implements WarehouseBeacon {

    @Override
    public void signalShelfLight(String zone, String sku) {
        System.out.println(
            "[MQTT Protocol] Надіслано сигнал на радіо-маячок стелажа у зоні '" +
            zone + "' для SKU '" + sku + "'."
        );
    }
}