package taskbuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import taskbuddy.task.Deadline;
import taskbuddy.task.Task;
import taskbuddy.task.Todo;

public class TaskListTest {

    /**
     * Test for finding task using keyword.
     */
    @Test
    public void testFindTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Buy groceries");
        Task task2 = new Todo("Finish essay");
        Task task3 = new Todo("Buy milk");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        ArrayList<Task> foundTasks = taskList.findTasks("Buy");
        assertEquals(2, foundTasks.size());
        assertTrue(foundTasks.contains(task1));
        assertTrue(foundTasks.contains(task3));
    }

    /**
     * Test for finding tasks by date.
     */
    @Test
    public void testFindMatchingTaskDate() {
        TaskList taskList = new TaskList();
        Task task1 = new Deadline("Buy groceries", "2025-01-10 1800");
        Task task2 = new Deadline("Finish essay", "2025-01-31 2200");
        LocalDate targetDate = LocalDate.of(2025, 1, 10);
        taskList.addTask(task1);
        taskList.addTask(task2);
        ArrayList<Task> foundTasks = taskList.findMatchingTaskDate(targetDate);
        assertEquals(1, foundTasks.size());
        assertTrue(foundTasks.contains(task1));
        assertFalse(foundTasks.contains(task2));
    }

    /**
     * Test for finding tasks with no matching keyword.
     */
    @Test
    public void testFindNoMatchingTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Buy groceries");
        Task task2 = new Todo("Finish essay");
        taskList.addTask(task1);
        taskList.addTask(task2);
        ArrayList<Task> foundTasks = taskList.findTasks("study");
        assertEquals(0, foundTasks.size());
    }

    /**
     * Test for finding tasks with no matching tasks to specific date.
     */
    @Test
    public void testFindNoMatchingTaskDate() {
        TaskList taskList = new TaskList();
        Task task1 = new Deadline("Buy groceries", "2025-01-10 1800");
        Task task2 = new Deadline("Finish essay", "2025-01-31 2200");
        LocalDate targetDate = LocalDate.of(2025, 2, 1);
        taskList.addTask(task1);
        taskList.addTask(task2);
        ArrayList<Task> foundTasks = taskList.findMatchingTaskDate(targetDate);
        assertEquals(0, foundTasks.size());
    }

    /**
     * Test for removing task with invalid index.
     */
    @Test
    public void testRemoveTaskInvalid() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Buy groceries");
        taskList.addTask(task);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.removeTask(2);
        });
    }

    /**
     * Test for marking tasks with invalid index.
     */
    @Test
    public void testMarkTaskInvalid() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Buy groceries");
        taskList.addTask(task);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.markTask(10);
        });
    }
}
