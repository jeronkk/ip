package lechatbot.task;

/**
 * Represents a generic task that the user manages.
 * This class is extended by specific task types such as Todo, Deadline, and Event.
 */
public abstract class Task {

    /**
     * Represents the description of the task.
     */
    protected String description;

    /**
     * Indicates whether the task has been marked as done.
     */
    protected boolean isDone;

    /**
     * Constructs a Task with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task's string representation.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Converts the task to a format suitable for file storage.
     *
     * @return A string representation of the task in file format.
     */
    public abstract String toFileFormat();
}
