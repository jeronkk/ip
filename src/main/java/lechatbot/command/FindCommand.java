package lechatbot.command;

import lechatbot.LeChatBotException;
import lechatbot.Storage;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        super(null);
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the keyword.
     *
     * @param taskList The list of tasks to search within.
     * @param ui       The user interface for displaying results.
     * @param storage  The storage handler (not used in this command).
     * @return A response containing the list of matching tasks or a "no match" message.
     * @throws LeChatBotException If no matching tasks are found.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeChatBotException {
        TaskList matchingTasks = taskList.findTasks(keyword);
        if (matchingTasks.getSize() == 0) {
            throw new LeChatBotException("No matching tasks found for keyword: " + keyword);
        }

        String response = ui.showMatchingTasks(matchingTasks);
        System.out.println(response);
        return response;
    }

    @Override
    public String toString() {
        return "FindCommand";
    }
}
