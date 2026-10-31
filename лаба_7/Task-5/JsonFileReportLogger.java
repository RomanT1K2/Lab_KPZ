package variant5_inventorystockmanager;

public class JsonFileReportLogger implements StockEventLogger {

    @Override
    public void logStockEvent(String jsonMessage) {
        System.out.println(
            "[JSON File Logger] Додано лог-запис у stock_events.json: " +
            jsonMessage
        );
    }
}