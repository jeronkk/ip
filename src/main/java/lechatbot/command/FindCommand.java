package lechatbot.command;

import lechatbot.LeChatBotException;
import lechatbot.Storage;
import lechatbot.Ui;
import lechatbot.task.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for tasks containing the keyword.
     *
     * @param taskList The list of tasks to search in.
     * @param ui       The user interface for displaying results.
     * @throws LeChatBotException If no matching tasks are found.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeChatBotException {
        TaskList matchingTasks = taskList.findTasks(keyword);
        if (matchingTasks.getSize() == 0) {
            throw new LeChatBotException("No matching tasks found for keyword: " + keyword);
        }

        ui.showMatchingTasks(matchingTasks); // Ensure this method exists in Ui.java
    }
}
