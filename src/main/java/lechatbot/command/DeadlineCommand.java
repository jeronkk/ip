package lechatbot.command;

import lechatbot.*;
import lechatbot.task.Deadline;
import lechatbot.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to add a {@link Deadline} task to the task list.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Constructs a {@code DeadlineCommand} with the specified deadline task.
     *
     * @param deadline The deadline task to be added.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the command by adding the deadline task to the task list and saving it.
     *
     * @param tasks   The task list where the deadline task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws LeChatBotException If an error occurs while saving the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        tasks.add(deadline);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
        ui.showLine();

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException("OOPS!!! An error occurred while saving tasks.");
        }
    }

    /**
     * Parses user input and creates a {@code DeadlineCommand}.
     *
     * @param taskDetails The user input containing the task description and deadline.
     * @return A {@code DeadlineCommand} object with the parsed task details.
     * @throws LeChatBotException If the input format is incorrect.
     */
    public static DeadlineCommand createFromUserInput(String taskDetails) throws LeChatBotException {
        String[] parts = taskDetails.split(" /by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LeChatBotException("OOPS!!! The deadline command must include a valid date using '/by'.");
        }
        return new DeadlineCommand(new Deadline(parts[0], parts[1]));
    }
}
