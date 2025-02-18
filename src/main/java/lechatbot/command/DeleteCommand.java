package lechatbot.command;

import java.io.IOException;

import lechatbot.LeChatBotException;
import lechatbot.Storage;
import lechatbot.task.Task;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        super(null);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     * It also updates the storage file and notifies the user of the deleted task.
     *
     * @param tasks   The task list from which the task will be removed.
     * @param ui      The UI instance to display messages.
     * @param storage The storage instance to persist changes.
     * @return A response message indicating the deleted task and updated task count.
     * @throws LeChatBotException If the task index is out of bounds or an error occurs while saving.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LeChatBotException("OOPS!!! The task number provided is invalid.");
        }

        Task removedTask = tasks.remove(taskIndex);
        String response = "Noted. I've removed this task:\n"
                + "  " + removedTask + "\n"
                + "Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.";

        ui.showLine();
        System.out.println(response);
        ui.showLine();

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException("OOPS!!! An error occurred while saving tasks.");
        }

        return response;
    }

    /**
     * Returns a string representation of the DeleteCommand.
     *
     * @return The string "DeleteCommand".
     */
    @Override
    public String toString() {
        return "DeleteCommand";
    }
}
