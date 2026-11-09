package stay;

public class SkiPass {
    private int days;
    private String zone;

    public SkiPass(int days, String zone) {
        this.days = days;
        this.zone = zone;
    }

    public SkiPass(SkiPass other) {
        this.days = other.days;
        this.zone = other.zone;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return days + " дн., зона " + zone;
    }
}
