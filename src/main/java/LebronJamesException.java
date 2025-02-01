public class LebronJamesException extends Exception {
    public LebronJamesException(String message) {
        super(message);
    }

    public static LebronJamesException emptyTaskDescription() {
        return new LebronJamesException("OOPS!!! The description of a todo cannot be empty.");
    }

    public static LebronJamesException taskListFull() {
        return new LebronJamesException("OOPS!!! The task list is full. You cannot add more than 100 tasks.");
    }

    public static LebronJamesException invalidCommand() {
        return new LebronJamesException("OOPS!!! Invalid command! Try: todo, deadline, event, list, mark, unmark, bye");
    }

    public static LebronJamesException invalidTaskNumber() {
        return new LebronJamesException("OOPS!!! The task number provided is invalid.");
    }
}