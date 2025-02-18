package lechatbot.command;

import java.io.IOException;

import lechatbot.LeChatBotException;
import lechatbot.Storage;
import lechatbot.task.Task;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        super(null);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark the specified task as done.
     *
     * @param tasks   The task list containing the task.
     * @param ui      The UI instance to interact with the user.
     * @param storage The storage instance to save changes.
     * @return A string message confirming that the task has been marked as done.
     * @throws LeChatBotException If the task index is invalid or an error occurs during saving.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LeChatBotException("OOPS!!! The task number provided is invalid.");
        }

        Task task = tasks.get(taskIndex);
        task.markAsDone();

        String response = "Nice! I've marked this task as done:\n  " + task;

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
        return "MarkCommand";
    }
}
