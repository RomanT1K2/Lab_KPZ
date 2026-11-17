package hotel.command;

import java.util.ArrayList;
import java.util.List;

/** Invoker: запускає команди та пам'ятає, що було виконано. */
public class BillingService {
    private final List<Command> executedCommands = new ArrayList<>();

    public void execute(Command command) {
        command.execute();
        executedCommands.add(command);
    }

    public void undo(Command command) {
        command.undo();
    }

    public int getExecutedCount() {
        return executedCommands.size();
    }
}
