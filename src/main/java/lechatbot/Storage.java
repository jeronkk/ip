package lechatbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lechatbot.task.Deadline;
import lechatbot.task.Event;
import lechatbot.task.Task;
import lechatbot.task.Todo;

/**
 * Handles loading and saving tasks to a file.
 * This class provides methods to read stored tasks and save new tasks persistently.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses a task from a given array of string components.
     *
     * @param parts The components of the task in stored file format.
     * @return The parsed Task object, or null if parsing fails.
     */
    private Task parseTask(String[] parts) {
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length >= 4) {
                task = new Deadline(description, parts[3]);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                task = new Event(description, parts[3], parts[4]);
            }
            break;
        default:
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Loads tasks from the storage file.
     * If the file does not exist, a new empty file is created.
     *
     * @return A list of tasks retrieved from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String firstLine = reader.readLine();

        if (firstLine == null) {
            reader.close();
            return tasks;
        }

        Task firstTask = parseTask(firstLine.split(" \\| "));
        if (firstTask != null) {
            tasks.add(firstTask);
        }

        String line;
        while ((line = reader.readLine()) != null) {
            Task task = parseTask(line.split(" \\| "));
            if (task != null) {
                tasks.add(task);
            }
        }
        reader.close();
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            writer.write(task.toFileFormat());
            writer.newLine();
        }
        writer.close();
    }
}
