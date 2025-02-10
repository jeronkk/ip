package lechatbot;

import lechatbot.command.*;
import lechatbot.task.Todo;

/**
 * Parses user input and returns the corresponding command.
 * This class is responsible for interpreting and validating user commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate {@link Command} object.
     *
     * @param userInput The full command input by the user.
     * @return The corresponding {@link Command} instance.
     * @throws LeChatBotException If the command is invalid or required parameters are missing.
     */
    public static Command parse(String userInput) throws LeChatBotException {
        String[] parts = userInput.split(" ", 2);

        String commandWord = parts[0].trim();
        String taskDetails = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandWord) {
            case "todo":
                if (taskDetails.isEmpty()) {
                    throw new LeChatBotException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new AddCommand(new Todo(taskDetails));

            case "deadline":
                if (taskDetails.isEmpty()) {
                    throw new LeChatBotException("OOPS!!! The description of a deadline cannot be empty.");
                }
                if (!taskDetails.contains("/by")) {
                    throw new LeChatBotException("OOPS!!! The deadline command must include a valid date using '/by'.");
                }
                return DeadlineCommand.createFromUserInput(taskDetails);

            case "event":
                if (taskDetails.isEmpty()) {
                    throw new LeChatBotException("OOPS!!! The description of an event cannot be empty.");
                }
                if (!taskDetails.contains("/from") || !taskDetails.contains("/to")) {
                    throw new LeChatBotException("OOPS!!! The event command must include both '/from' and '/to' with valid times.");
                }
                return EventCommand.createFromUserInput(taskDetails);

            case "list":
                return new ListCommand();

            case "mark":
            case "unmark":
            case "delete":
                if (taskDetails.isEmpty()) {
                    throw new LeChatBotException("OOPS!!! You must specify a task number.");
                }
                return processTaskCommand(commandWord, taskDetails);

            case "bye":
                return new ExitCommand();

            default:
                throw new LeChatBotException("OOPS!!! Invalid command! Try: todo, deadline, event, list, mark, unmark, bye");
        }
    }

    /**
     * Processes commands related to specific tasks such as marking, unmarking, or deleting a task.
     *
     * @param commandWord The task-related command (mark, unmark, delete).
     * @param taskDetails The task number provided as input.
     * @return The corresponding {@link Command} instance for the given task action.
     * @throws LeChatBotException If the task number is invalid or if the command is unrecognized.
     */
    private static Command processTaskCommand(String commandWord, String taskDetails) throws LeChatBotException {
        try {
            int taskIndex = Integer.parseInt(taskDetails) - 1; // Convert to 0-based index

            switch (commandWord) {
                case "mark":
                    return new MarkCommand(taskIndex);
                case "unmark":
                    return new UnmarkCommand(taskIndex);
                case "delete":
                    return new DeleteCommand(taskIndex);
                default:
                    throw new LeChatBotException("OOPS!!! Unknown task command.");
            }
        } catch (NumberFormatException e) {
            throw new LeChatBotException("OOPS!!! Task number must be a valid integer.");
        }
    }
}
