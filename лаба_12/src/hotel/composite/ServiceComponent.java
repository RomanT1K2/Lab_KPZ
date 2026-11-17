package hotel.composite;

import java.util.List;

/**
 * COMPONENT (спільний інтерфейс).
 * І окрема послуга, і цілий пакет реалізують його,
 * тому код розрахунку працює з ними однаково.
 */
public interface ServiceComponent {

    String getName();

    /** Вартість: для послуги - її ціна, для пакета - сума всіх частин. */
    double getPrice();

    /** Плоский перелік назв усіх окремих послуг усередині вузла. */
    List<String> getServiceNames();

    /** Друк вузла (і його вкладених вузлів) деревом. */
    void print(String indent);
}
