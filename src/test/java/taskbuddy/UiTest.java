package taskbuddy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import taskbuddy.task.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test class containing unit tests for testing the methods in Ui class.
 */
public class UiTest {

    /**
     * Test for printing a delete task message.
     */
    @Test
    public void testPrintDeleteTaskMessage() {
        Task task = new Todo("Borrow book");
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        ui.printDeleteTaskMessage(task);
        String output = outputStream.toString();
        assertTrue(output.contains("Noted. I've removed this task:"));
        assertTrue(output.contains("Borrow book"));
        System.setOut(System.out);
    }

    /**
     * Test for printing a task added message.
     */
    @Test
    void testShowTaskAdded() {
        Task task = new Todo("Buy groceries");
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        ui.printAddTaskMessage(task);
        String output = outputStream.toString();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains("Buy groceries"));
        System.setOut(System.out);
    }
}
