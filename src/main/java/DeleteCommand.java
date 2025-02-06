import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LeChatBotException("OOPS!!! The task number provided is invalid.");
        }
        Task removedTask = tasks.remove(taskIndex);
        ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
        ui.showLine();
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException("OOPS!!! An error occurred while saving tasks.");
        }
    }

}
