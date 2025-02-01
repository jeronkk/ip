public class LeChatBotException extends Exception {
    public LeChatBotException(String message) {
        super(message);
    }

    public static LeChatBotException emptyTaskDescription() {
        return new LeChatBotException("OOPS!!! The description of a todo cannot be empty.");
    }

    public static LeChatBotException taskListFull() {
        return new LeChatBotException("OOPS!!! The task list is full. You cannot add more than 100 tasks.");
    }

    public static LeChatBotException invalidCommand() {
        return new LeChatBotException("OOPS!!! Invalid command! Try: todo, deadline, event, list, mark, unmark, bye");
    }

    public static LeChatBotException invalidTaskNumber() {
        return new LeChatBotException("OOPS!!! The task number provided is invalid.");
    }
}