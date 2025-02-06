package lechatbot.command;

import lechatbot.*;
import lechatbot.task.Task;
import lechatbot.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list,
     * displaying the confirmation message, and saving the updated task list to storage.
     *
     * @param tasks   The task list to which the task is added.
     * @param ui      The UI component for displaying messages.
     * @param storage The storage component to save the updated task list.
     * @throws LeChatBotException If an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        tasks.add(task);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
        ui.showLine();
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException(LeChatBot.ErrorType.IO_ERROR);
        }
    }
}
