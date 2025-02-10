package lechatbot.command;

import lechatbot.LeChatBotException;
import lechatbot.Storage;
import lechatbot.task.TaskList;
import lechatbot.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark the specified task as done.
     *
     * @param tasks   The task list containing the task.
     * @param ui      The UI instance to interact with the user.
     * @param storage The storage instance to save changes.
     * @throws LeChatBotException If the task index is invalid or an error occurs during saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LeChatBotException("OOPS!!! The task number provided is invalid.");
        }
        tasks.get(taskIndex).markAsDone();
        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskIndex));
        ui.showLine();
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException("OOPS!!! An error occurred while saving tasks.");
        }
    }
}
