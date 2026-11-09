package stay;

public class Discount {
    private final String title;
    private int percent;

    public Discount(String title, int percent) {
        this.title = title;
        this.percent = percent;
    }

    public Discount(Discount other) {
        this.title = other.title;
        this.percent = other.percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return title + " " + percent + "%";
    }
}
