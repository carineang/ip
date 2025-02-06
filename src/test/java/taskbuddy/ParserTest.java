package taskbuddy;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import taskbuddy.command.*;
import taskbuddy.task.*;

public class ParserTest {

    // Test for list command
    @Test
    public void testListCommand() {
        TaskList tasklist = new TaskList();
        String input = "list";
        Command command = Parser.parseCommand(input, tasklist);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testDeleteCommand() {
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