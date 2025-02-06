public class Parser {
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
