package lechatbot;

import lechatbot.task.Task;
import lechatbot.task.TaskList;
import lechatbot.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void addTask_taskAddedSuccessfully() {
        Todo todo = new Todo("read book");
        taskList.add(todo);
        List<Task> tasks = taskList.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("read book", tasks.get(0).getDescription());
    }

    @Test
    public void removeTask_validIndex_taskRemoved() {
        Todo todo = new Todo("do homework");
        taskList.add(todo);
        taskList.remove(0);

        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    public void removeTask_invalidIndex_exceptionThrown() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.remove(0);
        });

        assertEquals("Index 0 out of bounds for length 0", exception.getMessage());
    }
}
