package lechatbot.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks that the user manages.
 * Provides methods to add, remove, retrieve, and check tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task by index from the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the list of tasks.
     *
     * @return A list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A {@code TaskList} containing tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
