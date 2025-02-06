package lechatbot.command;

import lechatbot.Storage;
import lechatbot.task.TaskList;
import lechatbot.Ui;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying the exit message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI handler for displaying messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Indicates that this command should terminate the chatbot.
     *
     * @return true, indicating that the chatbot should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
