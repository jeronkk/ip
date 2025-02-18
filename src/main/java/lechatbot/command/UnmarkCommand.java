package lechatbot.command;

import lechatbot.LeChatBotException;
import lechatbot.Storage;
import lechatbot.task.Task;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    public UnmarkCommand(int taskIndex) {
        super(null);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark the specified task as not done.
     *
     * @param tasks   The task list containing the task.
     * @param ui      The UI instance to interact with the user.
     * @param storage The storage instance to save changes.
     * @throws LeChatBotException If the task index is invalid or an error occurs during saving.
     * @return A string message confirming that the task has been marked as not done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LeChatBotException("OOPS!!! The task number provided is invalid.");
        }

        Task task = tasks.get(taskIndex);
        task.markAsNotDone();

        String response = "OK, I've marked this task as not done yet:\n  " + task;

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException("OOPS!!! An error occurred while saving tasks.");
        }

        ui.showLine();
        System.out.println(response);
        ui.showLine();
        return response;
    }

    @Override
    public String toString() {
        return "UnmarkCommand";
    }
}
