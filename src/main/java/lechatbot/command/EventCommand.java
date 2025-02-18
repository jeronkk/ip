package lechatbot.command;

import java.io.IOException;

import lechatbot.LeChatBotException;
import lechatbot.Storage;
import lechatbot.task.Event;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {

    /**
     * Constructs an {@code EventCommand} with the specified event task.
     *
     * @param event The event task to be added.
     */
    public EventCommand(Event event) {
        super(event); // Passes the event task to the parent Command class
    }

    /**
     * Executes the command by adding the event task to the task list,
     * displaying the confirmation message, and saving the task list.
     *
     * @param tasks   The task list where the event will be added.
     * @param ui      The UI handler for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @return A response message confirming the added event task.
     * @throws LeChatBotException If an error occurs while saving tasks.
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
     * Creates an {@code EventCommand} from user input.
     *
     * @param taskDetails The raw input string containing event details.
     * @return A new {@code EventCommand} object with parsed event details.
     * @throws LeChatBotException If the input format is incorrect.
     */
    public static EventCommand createFromUserInput(String taskDetails) throws LeChatBotException {
        String[] parts = taskDetails.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new LeChatBotException(
                    "OOPS!!! The event command must include both '/from' and '/to' with valid times.");
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        return new EventCommand(new Event(parts[0], timeParts[0], timeParts[1]));
    }

    /**
     * Returns a string representation of the {@code EventCommand}.
     *
     * @return The string "EventCommand".
     */
    @Override
    public String toString() {
        return "EventCommand";
    }
}
