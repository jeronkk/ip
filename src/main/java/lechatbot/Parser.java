package lechatbot;

import lechatbot.command.*;
import lechatbot.task.Todo;

/**
 * The {@code Parser} class processes user input and translates it into executable commands.
 * It supports commands for adding tasks, marking tasks, deleting tasks, and listing them.
 */
public class Parser {
    private static final String ERROR_EMPTY_TODO = "OOPS!!! The description of a todo cannot be empty.";
    private static final String ERROR_EMPTY_DEADLINE = "OOPS!!! The description of a deadline cannot be empty.";
    private static final String ERROR_EMPTY_EVENT = "OOPS!!! The description of an event cannot be empty.";
    private static final String ERROR_INVALID_DEADLINE = "OOPS!!! The deadline command must include a valid date using '/by'.";
    private static final String ERROR_INVALID_EVENT = "OOPS!!! The event command must include both '/from' and '/to' with valid times.";
    private static final String ERROR_MISSING_TASK_NUMBER = "OOPS!!! You must specify a task number.";
    private static final String ERROR_INVALID_COMMAND = "OOPS!!! Invalid command! Try: todo, deadline, event, list, mark, unmark, delete, bye.";

    /**
     * Parses user input and returns the corresponding {@code Command}.
     *
     * @param userInput The raw user input string.
     * @return A {@code Command} representing the user's input.
     * @throws LeChatBotException If the input is invalid or missing required details.
     */
    public static Command parse(String userInput) throws LeChatBotException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0].trim();
        String taskDetails = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandWord) {
            case "todo":
                if (taskDetails.isEmpty()) {
                    throw new LeChatBotException(ERROR_EMPTY_TODO);
                }
                return new AddCommand(new Todo(taskDetails));

            case "deadline":
                if (taskDetails.isEmpty()) {
                    throw new LeChatBotException(ERROR_EMPTY_DEADLINE);
                }
                if (!taskDetails.contains("/by")) {
                    throw new LeChatBotException(ERROR_INVALID_DEADLINE);
                }
                return DeadlineCommand.createFromUserInput(taskDetails);

            case "event":
                if (taskDetails.isEmpty()) {
                    throw new LeChatBotException(ERROR_EMPTY_EVENT);
                }
                if (!taskDetails.contains("/from") || !taskDetails.contains("/to")) {
                    throw new LeChatBotException(ERROR_INVALID_EVENT);
                }
                return EventCommand.createFromUserInput(taskDetails);

            case "list":
                return new ListCommand();

            case "mark":
            case "unmark":
            case "delete":
                if (taskDetails.isEmpty()) {
                    throw new LeChatBotException(ERROR_MISSING_TASK_NUMBER);
                }
                return processTaskCommand(commandWord, taskDetails);

            case "find":
                if (taskDetails.isEmpty()) {
                    throw new LeChatBotException("OOPS!!! You must specify a keyword to search.");
                }
                return new FindCommand(taskDetails);

            case "bye":
                return new ExitCommand();

            default:
                throw new LeChatBotException(ERROR_INVALID_COMMAND);
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
            int taskIndex = Integer.parseInt(taskDetails) - 1;

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
