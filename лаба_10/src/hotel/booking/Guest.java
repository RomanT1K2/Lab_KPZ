package hotel.booking;

/** Гість (незмінний). */
public record Guest(String name, String contact) {
    @Override public String toString() { return name + " <" + contact + ">"; }
}
