package lechatbot.command;

import lechatbot.LeChatBotException;
import lechatbot.Storage;
import lechatbot.task.Task;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

/**
 * Abstract class representing a command that can be executed in the chatbot.
 */
public abstract class Command {
    protected final Task task;

    public Command(Task task) {
        this.task = task;
    }

    protected void addTask(TaskList tasks) {
        tasks.add(task);
    }

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The task list that the command operates on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws LeChatBotException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException;

    /**
     * Indicates whether the command should cause the chatbot to exit.
     *
     * @return {@code false} by default. Commands that terminate the chatbot should override this method.
     */
    public boolean isExit() {
        return false;
    }
}
