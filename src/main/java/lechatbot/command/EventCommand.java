package lechatbot.command;

import lechatbot.*;
import lechatbot.task.Event;
import lechatbot.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Constructs an EventCommand with the specified event.
     *
     * @param event The event task to be added.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes the command by adding the event task to the task list,
     * displaying the confirmation message, and saving the task list.
     *
     * @param tasks   The task list where the event will be added.
     * @param ui      The UI handler for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws LeChatBotException If an error occurs while saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        tasks.add(event);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
        ui.showLine();

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new LeChatBotException("OOPS!!! An error occurred while saving tasks.");
        }
    }

    /**
     * Creates an EventCommand from user input.
     *
     * @param taskDetails The raw input string containing event details.
     * @return A new EventCommand object.
     * @throws LeChatBotException If the input format is incorrect.
     */
    public static EventCommand createFromUserInput(String taskDetails) throws LeChatBotException {
        String[] parts = taskDetails.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new LeChatBotException("OOPS!!! The event command must include both '/from' and '/to' with valid times.");
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        return new EventCommand(new Event(parts[0], timeParts[0], timeParts[1]));
    }
}
