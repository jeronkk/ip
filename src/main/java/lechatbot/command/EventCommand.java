package lechatbot.command;

import lechatbot.*;
import lechatbot.task.Event;
import lechatbot.task.TaskList;

import java.io.IOException;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

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

    public static EventCommand createFromUserInput(String taskDetails) throws LeChatBotException {
        String[] parts = taskDetails.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new LeChatBotException("OOPS!!! The event command must include both '/from' and '/to' with valid times.");
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        return new EventCommand(new Event(parts[0], timeParts[0], timeParts[1]));
    }
}
