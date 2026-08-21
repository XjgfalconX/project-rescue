import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;

public class DateManager {
    private LocalDate anchor;
    private int interval;
    private Integer amount;
    private ArrayList<Integer> changes;

    public DateManager(LocalDate anchor, int interval, int amount) {
        this.anchor = anchor;
        this.interval = interval;
        this.amount = amount;
        this.changes = new ArrayList<>();
    }

    public void updateReoccurrence(LocalDate current) {
        long days = ChronoUnit.DAYS.between(anchor, current);
        if(days % interval == 0) {
            Main.addValue(amount);
        }
    }

    public void printChanges() {
        for (Integer change : changes) {
            System.out.println(change);
        }
    }

    public void addValue(Integer value) {
        changes.add(value);
    }

    public ArrayList<Integer> getList() {
        return changes;
    }
}