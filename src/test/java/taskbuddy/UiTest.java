package taskbuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import taskbuddy.task.Deadline;
import taskbuddy.task.Task;
import taskbuddy.task.Todo;


/**
 * Test class containing unit tests for testing the methods in Ui class.
 */
public class UiTest {

    /**
     * Test for printing empty task list.
     */
    @Test
    public void testPrintTaskListEmpty() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        String result = ui.printTaskList(taskList);
        String expected = "Your task list is empty.";
        assertEquals(expected, result);
    }

    /**
     * Test for printing multiple tasks in the task list.
     */
    @Test
    public void testPrintTaskListWithTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Buy groceries");
        Task task2 = new Todo("Finish homework");
        taskList.addTask(task1);
        taskList.addTask(task2);
        Ui ui = new Ui();
        String result = ui.printTaskList(taskList);
        String expected = "Here are the tasks in your list:\n"
                + "   1. [T][ ] Buy groceries\n"
                + "   2. [T][ ] Finish homework";
        assertEquals(expected, result);
    }

    /**
     * Test for printing add task message.
     */
    @Test
    public void testPrintAddTaskMessage() {
        Task task = new Todo("Buy groceries");
        Ui ui = new Ui();
        String result = ui.printAddTaskMessage(task);
        String expected = "Got it. I've added this task:\n" + "   [T][ ] Buy groceries";
        assertEquals(expected, result);
    }

    /**
     * Test for printing marked task message.
     */
    @Test
    public void testPrintMarkTaskMessage() {
        Task task = new Todo("Buy groceries");
        task.markAsDone();
        Ui ui = new Ui();
        String result = ui.printMarkTaskMessage(task);
        String expected = "Nice! I've marked this task as done:\n" + "   [T][X] Buy groceries";
        assertEquals(expected, result);
    }

    /**
     * Test for printing unmarked task message.
     */
    @Test
    public void testPrintUnmarkTaskMessage() {
        Task task = new Todo("Buy groceries");
        task.markAsDone();
        task.markAsNotDone();
        Ui ui = new Ui();
        String result = ui.printUnmarkTaskMessage(task);
        String expected = "OK, I've marked this task as not done yet:\n" + "   [T][ ] Buy groceries";
        assertEquals(expected, result);
    }

    /**
     * Test for printing delete task message.
     */
    @Test
    public void testPrintDeleteTaskMessage() {
        Task task = new Todo("Buy groceries");
        Ui ui = new Ui();
        String result = ui.printDeleteTaskMessage(task);
        String expected = "Noted. I've removed this task:\n" + "   [T][ ] Buy groceries";
        assertEquals(expected, result);
    }

    /**
     * Test for printing matching tasks result.
     */
    @Test
    public void testPrintMatchingTasksResult() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        Task task1 = new Todo("Buy groceries");
        Task task2 = new Todo("Finish homework");
        matchingTasks.add(task1);
        matchingTasks.add(task2);
        Ui ui = new Ui();
        String result = ui.printMatchingTasks(matchingTasks);
        String expected = "Here are the matching tasks in your list:\n"
                + "   1. [T][ ] Buy groceries\n"
                + "   2. [T][ ] Finish homework";
        assertEquals(expected, result);
    }

    /**
     * Test for printing matching task date result.
     */
    @Test
    void testPrintMatchingTasksDate() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        matchingTasks.add(new Deadline("Buy groceries", "2025-01-10 1800"));
        matchingTasks.add(new Deadline("Finish essay", "2025-01-31 2200"));
        Ui ui = new Ui();
        LocalDate targetDate = LocalDate.of(2025, 1, 10);
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : matchingTasks) {
            if (task.matchesDate(targetDate)) {
                filteredTasks.add(task);
            }
        }
        String expected = "Here are the tasks for this date:\n"
                + "   1. [D][ ] Buy groceries (by: Jan 10 2025 6:00pm)";
        String result = ui.printMatchingTasksDate(filteredTasks);
        assertEquals(expected, result);
    }
}
