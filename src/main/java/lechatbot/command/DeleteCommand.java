package lechatbot.command;

import lechatbot.*;
import lechatbot.task.Task;
import lechatbot.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the task at the given index from the task list.
     * It also updates the storage file and notifies the user of the deleted task.
     *
     * @param tasks   The task list from which the task will be removed.
     * @param ui      The UI instance to interact with the user.
     * @param storage The storage instance to save the updated task list.
     * @throws LeChatBotException If the task index is out of bounds or an error occurs while saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LeChatBotException("OOPS!!! The task number provided is invalid.");
        }
        Task removedTask = tasks.remove(taskIndex);
        ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
        ui.showLine();
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException("OOPS!!! An error occurred while saving tasks.");
        }
    }
}
