import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

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

    public void save(List<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            writer.write(task.toFileFormat());
            writer.newLine();
        }
        writer.close();
    }
}
