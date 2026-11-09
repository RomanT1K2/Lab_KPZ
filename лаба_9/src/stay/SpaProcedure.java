package stay;

public class SpaProcedure {
    private final String name;
    private final int minutes;

    public SpaProcedure(String name, int minutes) {
        this.name = name;
        this.minutes = minutes;
    }

    /** Копіювальний конструктор. */
    public SpaProcedure(SpaProcedure other) {
        this.name = other.name;
        this.minutes = other.minutes;
    }

    @Override
    public String toString() {
        return name + " (" + minutes + " хв)";
    }
}
