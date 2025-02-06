package lechatbot.task;

/**
 * Represents a Todo task.
 * A Todo is a simple task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo task to a format suitable for file storage.
     *
     * @return A string representation of the Todo task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
