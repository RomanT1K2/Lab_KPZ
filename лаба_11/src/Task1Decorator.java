// ===== ЗАВДАННЯ 1: Додаткові послуги до номера -> шаблон DECORATOR =====
// Чому Decorator: послуги комбінуються довільно і "обгортають" номер,
// не змінюючи його інтерфейс.


interface Room {
    int getPrice();            
    String getDescription();  
}

// Звичайний номер (об'єкт, який обгортають)
class StandardRoom implements Room {
    private final int number;

    StandardRoom(int number) { this.number = number; }

    public int getPrice() {
        Logger.log("StandardRoom " + number + " (сам номер)", "повертає базову ціну 2000 грн");
        return 2000;
    }

    public String getDescription() { return "Стандартний номер " + number; }
}

// Базовий декоратор: зберігає номер, який обгортає, і додає свою послугу
abstract class RoomDecorator implements Room {
    protected final Room room;       
    private final String serviceName;
    private final int extraPrice;

    RoomDecorator(Room room, String serviceName, int extraPrice) {
        this.room = room;
        this.serviceName = serviceName;
        this.extraPrice = extraPrice;
    }

    public int getPrice() {
        int inner = room.getPrice();  
        Logger.log("Декоратор '" + serviceName + "' (обгортка)",
                "додає " + extraPrice + " грн до " + inner + " грн");
        return inner + extraPrice;
    }

    public String getDescription() {
        return room.getDescription() + ", " + serviceName;
    }
}

class Breakfast extends RoomDecorator {
    Breakfast(Room r) { super(r, "сніданок", 300); }
}
class MountainView extends RoomDecorator {
    MountainView(Room r) { super(r, "вид на гори", 200); }
}
class LateCheckout extends RoomDecorator {
    LateCheckout(Room r) { super(r, "пізній виїзд", 400); }
}
class Parking extends RoomDecorator {
    Parking(Room r) { super(r, "паркування", 150); }
}
class BabyBed extends RoomDecorator {
    BabyBed(Room r) { super(r, "дитяче ліжко", 100); }
}
