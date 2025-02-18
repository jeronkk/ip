package lechatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lechatbot.task.Task;
import lechatbot.task.TaskList;
import lechatbot.task.Todo;

public class StorageTest {
    private static final String TEST_FILE_PATH = "text-ui-test/testStorage.txt";
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setUp() throws IOException {
        storage = new Storage(TEST_FILE_PATH);
        taskList = new TaskList();
    }

    @Test
    public void saveTasks_loadsSameTasks_success() throws IOException {
        Todo todo = new Todo("watch movie");
        taskList.add(todo);

        storage.save(taskList.getTasks());

        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("watch movie", loadedTasks.get(0).getDescription());
    }

    @Test
    public void loadTasks_noFile_emptyListReturned() {
        File file = new File(TEST_FILE_PATH);
        file.delete();

        try {
            List<Task> loadedTasks = storage.load();
            assertEquals(0, loadedTasks.size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
