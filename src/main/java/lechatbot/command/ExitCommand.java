package lechatbot.command;

import lechatbot.Storage;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an {@code ExitCommand}.
     */
    public ExitCommand() {
        super(null);
    }

    /**
     * Executes the exit command by displaying the exit message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI handler for displaying messages.
     * @param storage The storage handler (not used in this command).
     * @return Exit message indicating termination.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExitMessage();
    }

    /**
     * Indicates that this command should terminate the chatbot.
     *
     * @return {@code true}, indicating that the chatbot should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "ExitCommand";
    }
}
