package lechatbot.command;

import lechatbot.Storage;
import lechatbot.task.TaskList;
import lechatbot.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   The task list whose tasks will be displayed.
     * @param ui      The UI instance to interact with the user.
     * @param storage The storage instance (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
