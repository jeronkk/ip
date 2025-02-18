package lechatbot.command;

import lechatbot.*;
import lechatbot.task.Task;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

import java.io.IOException;

/**
 * A command that adds a {@link Task} to the {@link TaskList}.
 * This command updates the task list, provides a confirmation message,
 * and ensures the task list is saved to persistent storage.
 */
public class AddCommand extends Command {

    /**
     * Constructs an {@code AddCommand} with the specified {@link Task}.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        super(task);
    }

    /**
     * Executes the command by adding the task to the task list,
     * displaying a confirmation message, and saving the updated task list.
     *
     * @param tasks   The {@link TaskList} to which the task is added.
     * @param ui      The {@link Ui} component responsible for displaying messages.
     * @param storage The {@link Storage} component for persisting the updated task list.
     * @return A formatted string confirming the successful addition of the task.
     * @throws LeChatBotException If an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        this.addTask(tasks);
        String response = "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.";

        ui.showLine();
        System.out.println(response);
        ui.showLine();

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException(LeChatBot.ErrorType.IO_ERROR);
        }

        return response;
    }

    /**
     * Returns a string representation of this command.
     *
     * @return The string {@code "AddCommand"}.
     */
    @Override
    public String toString() {
        return "AddCommand";
    }
}
