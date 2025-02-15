package taskbuddy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import taskbuddy.command.Command;
import taskbuddy.command.ListCommand;
import taskbuddy.task.Task;
import taskbuddy.task.Todo;

/**
 * Test class containing unit tests for parsing commands in Parser class.
 */
public class ParserTest {

    /**
     * Test for the "list" command.
     */
    @Test
    public void testListCommand() throws TaskBuddyException {
        TaskList tasklist = new TaskList();
        String input = "list";
        Command command = Parser.parseCommand(input, tasklist);
        assertTrue(command instanceof ListCommand);
    }

    /**
     * Test for the "delete" command.
     */
    @Test
    public void testDeleteCommand() throws TaskBuddyException {
        TaskList taskList = new TaskList();
        Task todoTask1 = new Todo("Finish Homework");
        Task todoTask2 = new Todo("Read Book");
        taskList.addTask(todoTask1);
        taskList.addTask(todoTask2);
        String input = "delete 1";
        Parser.parseCommand(input, taskList);
        assertTrue(taskList.contains(todoTask2));
    }
}
