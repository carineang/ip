package taskbuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import taskbuddy.command.AddCommand;
import taskbuddy.command.Command;
import taskbuddy.command.MarkCommand;
import taskbuddy.task.Deadline;
import taskbuddy.task.Task;
import taskbuddy.task.Todo;

/**
 * Test class containing unit tests for parsing commands in Parser class.
 */
public class ParserTest {

    /**
     * Test for the "todo" command.
     *
     * @throws TaskBuddyException if there is an error.
     */
    @Test
    public void testToDoCommand() throws TaskBuddyException {
        TaskList taskList = new TaskList();
        String input = "todo buy groceries";
        Command command = Parser.parseCommand(input, taskList);
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        Task addedTask = addCommand.getTask();
        assertTrue(addedTask instanceof Todo);
        assertEquals("buy groceries", addedTask.getDescription());
    }

    /**
     * Test for the "todo" command without task description.
     */
    @Test
    public void testTodoCommandInvalid() {
        TaskList taskList = new TaskList();
        String input = "todo";
        TaskBuddyException exception = assertThrows(TaskBuddyException.class, () -> {
            Parser.parseCommand(input, taskList);
        });
        assertEquals("Please provide a valid todo description.", exception.getMessage());
    }

    /**
     * Test for the "deadline" command.
     *
     * @throws TaskBuddyException if there is an error.
     */
    @Test
    public void testDeadlineCommand() throws TaskBuddyException {
        TaskList taskList = new TaskList();
        String input = "deadline submit homework /by 2025-02-20 1200";
        Command command = Parser.parseCommand(input, taskList);
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        Task addedTask = addCommand.getTask();
        assertTrue(addedTask instanceof Deadline);
        assertEquals("submit homework", addedTask.getDescription());
    }

    /**
     * Test for the "deadline" command with missing time.
     */
    @Test
    public void testDeadlineCommandInvalid() {
        TaskList taskList = new TaskList();
        String input = "deadline submit homework /by 2025-02-20";
        TaskBuddyException exception = assertThrows(TaskBuddyException.class, () -> {
            Parser.parseCommand(input, taskList);
        });
        assertEquals("Invalid date format for deadline. Please use the format: yyyy-MM-dd HHmm.",
                exception.getMessage());
    }

    /**
     * Test for the "find" command without keyword.
     */
    @Test
    public void testFindCommandInvalid() {
        TaskList taskList = new TaskList();
        String input = "find";
        TaskBuddyException exception = assertThrows(TaskBuddyException.class, () -> {
            Parser.parseCommand(input, taskList);
        });
        assertEquals("Please provide a valid keyword.", exception.getMessage());
    }

    /**
     * Test for the "mark" command.
     *
     * @throws TaskBuddyException if there is an error.
     */
    @Test
    public void testMarkCommand() throws TaskBuddyException {
        TaskList taskList = new TaskList();
        Task todoTask = new Todo("buy groceries");
        taskList.addTask(todoTask);
        String input = "mark 1";
        Command command = Parser.parseCommand(input, taskList);
        assertTrue(command instanceof MarkCommand);
        MarkCommand markCommand = (MarkCommand) command;
        assertEquals(todoTask, markCommand.getTask());
    }

    /**
     * Test for the "mark" command with invalid task number.
     */
    @Test
    public void testMarkCommandInvalid() {
        TaskList taskList = new TaskList();
        String input = "mark 99";
        TaskBuddyException exception = assertThrows(TaskBuddyException.class, () -> {
            Parser.parseCommand(input, taskList);
        });
        assertEquals("Invalid task number. Please try again.", exception.getMessage());
    }
}
