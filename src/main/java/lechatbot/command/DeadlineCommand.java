package lechatbot.command;

import lechatbot.*;
import lechatbot.task.Deadline;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a {@link Deadline} task to the task list.
 * This command encapsulates the process of creating a deadline-based task and storing it.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a {@code DeadlineCommand} with the specified deadline task.
     *
     * @param deadline The deadline task to be added to the task list.
     */
    public DeadlineCommand(Deadline deadline) {
        super(deadline);
    }

    /**
     * Executes this command by adding the deadline task to the provided task list,
     * displaying a confirmation message to the user, and saving the updated list to storage.
     *
     * @param tasks   The task list where the deadline task will be added.
     * @param ui      The user interface used for displaying messages.
     * @param storage The storage handler for saving tasks to persistent storage.
     * @return A response message indicating the successful addition of the task.
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
            throw new LeChatBotException("OOPS!!! An error occurred while saving tasks.");
        }

        return response;
    }

    /**
     * Parses user input and creates a {@code DeadlineCommand} instance.
     * The user input must contain a valid task description and a deadline specified using the "/by" keyword.
     *
     * @param taskDetails The user input containing the task description and deadline.
     * @return A {@code DeadlineCommand} object initialized with the parsed deadline task.
     * @throws LeChatBotException If the input format is incorrect or missing a valid deadline.
     */
    public static DeadlineCommand createFromUserInput(String taskDetails) throws LeChatBotException {
        String[] parts = taskDetails.split(" /by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LeChatBotException("OOPS!!! The deadline command must include a valid date using '/by'.");
        }
        return new DeadlineCommand(new Deadline(parts[0], parts[1]));
    }

    /**
     * Returns a string representation of this command, primarily for debugging purposes.
     *
     * @return The string {@code "DeadlineCommand"}.
     */
    @Override
    public String toString() {
        return "DeadlineCommand";
    }
}
