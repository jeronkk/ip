package lechatbot.command;

import lechatbot.Storage;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super(null); // Since ListCommand does not involve a specific task.
    }

    /**
     * Executes the list command by retrieving all tasks in the task list.
     *
     * @param tasks   The task list whose tasks will be displayed.
     * @param ui      The UI instance to format the response.
     * @param storage The storage instance (not used in this command).
     * @return A formatted string representation of all tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }

    @Override
    public String toString() {
        return "ListCommand";
    }
}
