import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LeChatBotException("OOPS!!! The task number provided is invalid.");
        }
        tasks.get(taskIndex).markAsNotDone();
        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskIndex));
        ui.showLine();
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException("OOPS!!! An error occurred while saving tasks.");
        }
    }
}